package io.github.shniu.spi.impl;

import io.github.shniu.spi.RedisDriver;

/**
 * @author shniu
 * @date 2019/08/08 17:01:45
 */
public class AliyunDriverImpl implements RedisDriver {
    @Override
    public String gerDriverName() {
        return getClass().getName();
    }
}
