package io.github.shniu.flashchat.common;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author niushaohan
 * @date 2020/12/30 17
 */
public final class Id {
    private static AtomicLong globalId = new AtomicLong(10000);

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static String id() {
        return String.valueOf(globalId.getAndIncrement());
    }
}
