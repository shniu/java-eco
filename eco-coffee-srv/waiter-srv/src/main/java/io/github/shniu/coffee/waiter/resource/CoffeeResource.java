package io.github.shniu.coffee.waiter.resource;

import io.github.shniu.coffee.commons.api.ResultCode;
import io.github.shniu.coffee.commons.exception.MyServiceException;
import io.github.shniu.coffee.commons.exception.ServiceException;
import io.github.shniu.coffee.waiter.resource.dto.CoffeeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author niushaohan
 * @date 2020/6/20 00
 */
@RestController
@RequestMapping("/v1/api")
@Slf4j
public class CoffeeResource {

    @GetMapping(value = "/coffee")
    public List<CoffeeResponse> getCoffees() {
        throw new ServiceException(ResultCode.FAILURE);
        // return Lists.newArrayList();
    }

    @GetMapping(value = "/coffee/{idOrName}")
    public CoffeeResponse getCoffee(@PathVariable final String idOrName) {
        log.info("Get coffee by idOrName: {}", idOrName);
        throw new MyServiceException(ResultCode.FAILURE, "My Custom");
        // return new CoffeeResponse();
    }
}
