package io.github.shniu.mybatis.service;

import io.github.shniu.mybatis.config.ConnectionFacade;
import io.github.shniu.mybatis.domain.entity.User;
import io.github.shniu.mybatis.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.killbill.bus.DefaultPersistentBus;
import org.killbill.bus.api.BusEvent;
import org.killbill.bus.api.PersistentBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.Properties;
import java.util.UUID;

/**
 * @author niushaohan
 * @date 2020/9/24 16
 */
@Service
@Slf4j
public class UserService {
    private UserMapper userMapper;

    private ConnectionFacade connectionFacade;

    @Autowired
    public UserService(final UserMapper userMapper,
                       final ConnectionFacade connectionFacade) {
        this.userMapper = userMapper;
        this.connectionFacade = connectionFacade;
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertUser(final User user) {
        int rows = userMapper.insert(user);

        PersistentBus persistentBus =
                new DefaultPersistentBus(connectionFacade.getDataSource(), new Properties());
        // persistentBus.initQueue();
        persistentBus.startQueue();

        log.info("Persistent bus is started: {}", persistentBus.isStarted());

        BusEvent event = new BusEvent() {
            @Override
            public Long getSearchKey1() {
                return user.getUid();
            }

            @Override
            public Long getSearchKey2() {
                return user.getUid();
            }

            @Override
            public UUID getUserToken() {
                return UUID.randomUUID();
            }
        };

        try {
            Connection connection = connectionFacade.getConnection();
            persistentBus.postFromTransaction(event, connection);
        } catch (PersistentBus.EventBusException e) {
            log.error("Outbox event publisher error", e);
        }

        return rows;
    }
}
