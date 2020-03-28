package io.github.shniu.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PetController {

    @GetMapping(value = "/pet/{name}")
    public String getPet(@PathVariable String name) {
        return "Pet's name is " + name;
    }

}
