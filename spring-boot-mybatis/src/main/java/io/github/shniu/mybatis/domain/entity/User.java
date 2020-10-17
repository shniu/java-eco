package io.github.shniu.mybatis.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author niushaohan
 * @date 2020/6/7 13
 */
@Data
@Accessors(chain = true)
public class User {
    private Long uid;
    private String name;
    private String idCard;
    private String mobile;
    private String email;

    private long createdAt;
}
