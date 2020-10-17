package io.github.shniu.mybatis.resource.dto;

import lombok.Data;

/**
 * @author niushaohan
 * @date 2020/9/24 17
 */
@Data
public class CreateUserRequest {
    private String name;
    private String idCard;
    private String email;
    private String mobile;
}
