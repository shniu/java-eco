package io.github.shniu.mybatisplus.aspect;

import io.github.shniu.mybatisplus.mapper.EventMapper;
import io.github.shniu.mybatisplus.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author niushaohan
 * @date 2021/3/30 23
 */
@Service
@Slf4j
public class CollectEventService {
    private EventMapper eventMapper;

    public CollectEventService(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    // @Transactional(propagation = Propagation.REQUIRED)
    public void logEventInTransaction(Event event) {
        eventMapper.insert(event);
        log.info("Save event {}", event);
        // throw new RuntimeException("mock");
    }
}
