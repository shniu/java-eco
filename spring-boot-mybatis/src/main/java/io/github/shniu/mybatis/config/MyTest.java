package io.github.shniu.mybatis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author niushaohan
 * @date 2020/9/24 18
 */
@ConfigurationProperties(prefix = "my.datasource")
@Data
public class MyTest {
    private String url;
}
