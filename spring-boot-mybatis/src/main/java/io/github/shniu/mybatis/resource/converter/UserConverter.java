package io.github.shniu.mybatis.resource.converter;

import io.github.shniu.mybatis.domain.entity.User;
import io.github.shniu.mybatis.resource.dto.CreateUserRequest;

/**
 * @author niushaohan
 * @date 2020/9/24 17
 */
public final class UserConverter {

    public static User to(final CreateUserRequest createUserRequest) {
        return new User().setName(createUserRequest.getName())
                .setIdCard(createUserRequest.getIdCard())
                .setEmail(createUserRequest.getEmail())
                .setMobile(createUserRequest.getMobile())
                .setCreatedAt(System.currentTimeMillis());
    }
}
