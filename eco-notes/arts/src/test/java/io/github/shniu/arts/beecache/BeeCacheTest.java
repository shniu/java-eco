package io.github.shniu.arts.beecache;

import io.github.shniu.arts.core.cache.bee.BeeCache;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by shniu on 2019/3/26 0026.
 */
public class BeeCacheTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testBeeCache_whenPutAndGet_thenSucceed() throws Exception {
        BeeCache<String, String> beeCache = new BeeCache<>(null, "sample");
        beeCache.put("k1", "v1");

        Assert.assertEquals("v1", beeCache.get("k1"));
        Assert.assertNull(beeCache.get("non"));
    }

    @Test
    public void testBeeCache_givenUserObj_thenCacheSucceed() {
        BeeCache<Integer, User> beeCache = new BeeCache<>(null, "sample");

        User u1 = new User(1, 30, "xxx");
        User u2 = new User(2, 24, "yyy");

        beeCache.put(1, u1);
        beeCache.put(2, u2);

        User user = beeCache.get(1);
        Assert.assertEquals(u1, user);
    }

    static class User {
        private int uid;
        private int age;
        private String name;

        public User(int uid, int age, String name) {
            this.uid = uid;
            this.age = age;
            this.name = name;
        }
    }

    @Test
    public void testBeeCache_whenRemove_thenGetNull() {
        BeeCache<String, String> beeCache = new BeeCache<>(null, "sample");
        beeCache.put("k1", "v1");
        beeCache.remove("k1");
        Assert.assertNull(beeCache.get("k1"));
    }

}