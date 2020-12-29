package io.github.shniu.arts.sourcecode;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

@Slf4j
public class UnsafeDemo {
    private int count;
    private static long staticCount = 100;
    private int[] arrr = {1, 2, 3, 4};

    private static Unsafe UNSAFE = ReflectUnsafe.getUnsafe();

    public UnsafeDemo() {
        count = 10;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        UnsafeDemo unsafeDemo = new UnsafeDemo();

        try {
            // 直接访问 Unsafe 的实例会报错，因为 Unsafe 的实例访问强制需要引导类加载器加载的类才可以访问
            // 安全控制
            Unsafe unsafe = Unsafe.getUnsafe();
            long countOffset = unsafe.objectFieldOffset(UnsafeDemo.class.getDeclaredField("count"));
            unsafe.getInt(unsafeDemo, countOffset);
        } catch (Exception e) {
            // swallow exception
            log.info("", e);
        }

        useUnsafeByReflect(unsafeDemo);
    }

    private static void useUnsafeByReflect(UnsafeDemo unsafeDemo) throws NoSuchFieldException {
        // count 字段及在对象中的偏移地址
        Field countField = UnsafeDemo.class.getDeclaredField("count");
        long countOffset = UNSAFE.objectFieldOffset(countField);

        int cnt = UNSAFE.getInt(unsafeDemo, countOffset);
        assert cnt == 10;
        // 这种方式是不安全的，和处理器有关系，大端和小端的差异
        short aShort = UNSAFE.getShort(unsafeDemo, countOffset);
        log.info("{}", aShort);

        // ================== static ==================
        // staticCount 字段及在对象中的偏移地址
        Field staticCountField = UnsafeDemo.class.getDeclaredField("staticCount");
        long staticFieldOffset = UNSAFE.staticFieldOffset(staticCountField);  // 获取静态字段的偏移量
        int staticInt = UNSAFE.getInt(unsafeDemo, staticFieldOffset);
        assert staticInt == 100;

    }
}

class SubUnsafeDemo extends UnsafeDemo {
    private int subCnt = 11;
}
