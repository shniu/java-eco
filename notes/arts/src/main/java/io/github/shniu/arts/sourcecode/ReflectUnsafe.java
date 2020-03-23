package io.github.shniu.arts.sourcecode;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

@Slf4j
public class ReflectUnsafe {
    static Unsafe UNSAFE;

    static {
        try {
            // 通过反射的方式拿到Unsafe的实例，绕开了引导类加载器的限制
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // swallow exception
            log.info("", e);
        }
    }

    public static Unsafe getUnsafe() {
        return UNSAFE;
    }
}
