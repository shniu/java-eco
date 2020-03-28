package io.github.shniu.middleware.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
public class ZookeeperTest {
    private String connectString = "192.168.33.10:2181,192.168.33.10:2182,192.168.33.10:2183";

    private ZooKeeper zkClient = null;

    @Before
    public void setUp() {
        try {
            zkClient = new ZooKeeper(connectString, 2000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    log.info("Watch event {} {}", watchedEvent.getPath(), watchedEvent.getState().name());

                    try {
                        zkClient.getChildren("/", true);
                    } catch (KeeperException | InterruptedException e) {
                        log.error("", e);
                    }
                }
            });
            log.info("zooKeeper instance: {}", zkClient);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    @Test
    public void testCreateZnode() throws UnsupportedEncodingException, KeeperException, InterruptedException {
        String service1 = "/zk_test/service1";
        if (zkClient.exists(service1, false) == null) {
            String nodeCreated = zkClient.create(service1,
                    "192.168.1.1:8080".getBytes(Charsets.UTF_8.name()),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
            log.info("node created: {}", nodeCreated);
        }

        zkClient.delete(service1, -1);
    }

    @Test
    public void testGetChildren() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            log.info(child);
        }
    }

    @Test
    public void testExist() throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists("/zk_test", false);
        log.info("stat {}", stat);
    }


}
