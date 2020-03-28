package io.github.shniu.middleware.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.junit.Test;

import java.io.IOException;

public class ExecutorTest {
    private String connectString = "192.168.33.10:2181,192.168.33.10:2182,192.168.33.10:2183";

    @Test
    public void testExecutor() throws IOException, KeeperException {
        new Executor(connectString, "/app", "/Users/shniu/tmp/tmp.out", new String[]{}).run();
    }
}