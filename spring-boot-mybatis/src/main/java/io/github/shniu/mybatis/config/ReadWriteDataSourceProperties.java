package io.github.shniu.mybatis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author niushaohan
 * @date 2020/9/24 18
 */
//@ConfigurationProperties(prefix = "spring.rw-datasource")
@Data
public class ReadWriteDataSourceProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
