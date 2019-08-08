package io.github.shniu;

import io.github.shniu.spi.RedisDriver;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        ServiceLoader<RedisDriver> drivers = ServiceLoader.load(RedisDriver.class);
        // Optional<RedisDriver> driver = StreamSupport.stream(drivers.spliterator(), false).findFirst();
        // driver.ifPresent(rd -> System.out.println(rd.gerDriverName()));

        StreamSupport.stream(drivers.spliterator(), false)
                .forEach(redisDriver -> System.out.println(redisDriver.gerDriverName()));
    }
}
