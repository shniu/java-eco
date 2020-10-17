package io.github.shniu.mybatis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author niushaohan
 * @date 2020/9/18 17
 */
@Configuration
//@EnableConfigurationProperties({ReadWriteDataSourceProperties.class, MyTest.class})
@EnableTransactionManagement
@Slf4j
public class DataSourceConfiguration {
    static final String RW_DATASOURCE = "rwDataSourceProperties2";
    static final String RO_DATASOURCE = "roDataSourceProperties2";

    public static final String MASTER = "masterDatasource";
    static final String SLAVE = "slaveDatasource";

    @Bean(RW_DATASOURCE)
    @ConfigurationProperties("spring.rw-datasource")
    public DataSourceProperties rwDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(RO_DATASOURCE)
    @ConfigurationProperties("spring.ro-datasource")
    public DataSourceProperties roDatasourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(MASTER)
    public DataSource masterDataSource(
            @Autowired @Qualifier(RW_DATASOURCE) DataSourceProperties rwDataSourceProperties) {
        log.info("rwDataSourceProperties: {}", rwDataSourceProperties.getUrl());
        return rwDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(SLAVE)
    public DataSource slaveDataSource(
            @Autowired @Qualifier(RO_DATASOURCE) DataSourceProperties roDataSourceProperties) {
        return roDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) throws SQLException {
        log.info("datasource: {}", dataSource.getConnection());
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public ConnectionFacade connectionFacade(@Qualifier(MASTER) DataSource dataSource) {
        return new ConnectionFacade(dataSource);
    }
}
