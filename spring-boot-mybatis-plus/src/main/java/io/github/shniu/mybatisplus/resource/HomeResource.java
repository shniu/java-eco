package io.github.shniu.mybatisplus.resource;

import io.github.shniu.mybatisplus.model.User;
import io.github.shniu.mybatisplus.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author niushaohan
 * @date 2021/3/30 23
 */
@RestController
@RequestMapping("/api/v1")
public class HomeResource {

    private UserService userService;

    public HomeResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHome() {
        User user = userService.saveUser();
        return "home page: " + user.getId();
    }
}
