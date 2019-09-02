package io.github.shniu.juc.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class HikariTest {

    @Test
    public void testHikari() {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/employees");
        config.setUsername("root");
        config.setPassword("123456");
        config.setConnectionTestQuery("select 1");
        // com.mysql.cj.jdbc.Driver
        // config.setDataSourceClassName("");

        DataSource ds = new HikariDataSource(config);
        try {
            Connection conn = ds.getConnection();
            log.info("{}", conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testServer() {

    }
}
