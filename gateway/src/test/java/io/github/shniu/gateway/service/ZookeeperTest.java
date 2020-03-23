package io.github.shniu.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class ZookeeperTest {
    private String connectString = "192.168.33.10:2181,192.168.33.10:2182,192.168.33.10:2183";

    @Test
    public void testZk1() {
        try {
            ZooKeeper zooKeeper = new ZooKeeper(connectString, 2000, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    log.info("Watch event {} {}", watchedEvent.getPath(), watchedEvent.getState().name());
                }
            });
            log.info("zooKeeper instance: {}", zooKeeper);
        } catch (IOException e) {
            log.error("", e);
        }
    }


}
