package io.github.shniu.mybatis.resource;

import io.github.shniu.mybatis.resource.converter.UserConverter;
import io.github.shniu.mybatis.resource.dto.CreateUserRequest;
import io.github.shniu.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author niushaohan
 * @date 2020/9/24 17
 */
@RestController
@Slf4j
public class UserResource {
    private UserService userService;

    public UserResource(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/user")
    public String createUser(@RequestBody CreateUserRequest createUserRequest) {
        int affectedRows = userService.insertUser(UserConverter.to(createUserRequest));
        log.info("User: {}, affectedRows: {}", createUserRequest, affectedRows);

        return "success";
    }
}
