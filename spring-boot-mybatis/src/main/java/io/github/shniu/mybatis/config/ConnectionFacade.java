package io.github.shniu.mybatis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author niushaohan
 * @date 2020/9/24 17
 */
@Slf4j
public class ConnectionFacade {
    private DataSource dataSource;

    public ConnectionFacade(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        Connection connection = DataSourceUtils.getConnection(dataSource);

        Thread thread = Thread.currentThread();
        log.info("Current thread ({}) uses the connection {}", thread.getName(), connection);

        return connection;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }
}
