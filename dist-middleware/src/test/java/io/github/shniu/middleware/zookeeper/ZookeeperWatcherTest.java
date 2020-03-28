package io.github.shniu.middleware.zookeeper;

import lombok.extern.slf4j.Slf4j;
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
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class ZookeeperWatcherTest {
    private String connectString = "192.168.33.10:2181,192.168.33.10:2182,192.168.33.10:2183";

    private ZooKeeper zooKeeper;
    private volatile CountDownLatch closeLatch;

    @Before
    public void setUp() throws InterruptedException, IOException {
        DefaultWatcher gw = new DefaultWatcher(this);
        zooKeeper = new ZooKeeper(connectString, 2000, gw);
        gw.await();
    }

    private void setCloseLatch(int count) {
        closeLatch = new CountDownLatch(count);
    }

    public void countDownCloseLatch() {
        closeLatch.countDown();
    }

    @Test
    public void testWatchChild() throws KeeperException, InterruptedException {
        setCloseLatch(1);
        String fatherPath = "/app1";
        if (zooKeeper.exists(fatherPath, false) == null) {
            zooKeeper.create(fatherPath, "".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        List<String> children = zooKeeper.getChildren(fatherPath, true);
        log.info("children {}", children);
        closeLatch.await();
        for (String child : children) {
            zooKeeper.delete(fatherPath + "/" + child, -1);
        }
        log.info("closing zookeeper");
        zooKeeper.close();
    }

    @Test
    public void testWatchers() throws KeeperException, InterruptedException {
        String app11 = "/app11";
        String app12 = "/app12";
        setCloseLatch(2);

        Stat app11Stat = zooKeeper.exists(app11, true);
        log.info("{} stat: {}", app11, app11Stat);

        Stat app12Stat = zooKeeper.exists(app12, new ExistsWatcher(this));
        log.info("{} stat: {}", app12, app12Stat);

        closeLatch.await();
        zooKeeper.delete(app11, -1);
        zooKeeper.delete(app12, -1);
        log.info("closing zookeeper");
        zooKeeper.close();
    }

    @Test
    public void testGlobalWatcher() throws KeeperException, InterruptedException {
        String app13 = "/app13";
        setCloseLatch(1);

        Stat app13Stat = zooKeeper.exists(app13, true);
        log.info("{} stat: {}", app13, app13Stat);
        app13Stat = zooKeeper.exists(app13, true);
        log.info("{} stat: {}", app13, app13Stat);

        closeLatch.await();
        zooKeeper.delete(app13, -1);
        log.info("closing zookeeper");
        zooKeeper.close();
    }

    @Test
    public void testRemovedWatcher() throws KeeperException, InterruptedException {
        String app14 = "/app14";
        setCloseLatch(1);

        Stat stat = zooKeeper.exists(app14, event -> {
            log.info("in remove event {}", event);
            if (event.getType() == Watcher.Event.EventType.NodeDeleted) {
                countDownCloseLatch();
            }
        });

        closeLatch.await();
        log.info("closing zookeeper");
        zooKeeper.close();
    }

    class DefaultWatcher implements Watcher {
        private CountDownLatch startLatch = new CountDownLatch(1);
        private ZookeeperWatcherTest watcherTest;

        public DefaultWatcher(ZookeeperWatcherTest watcherTest) {
            this.watcherTest = watcherTest;
        }

        @Override
        public void process(WatchedEvent watchedEvent) {
            log.info("event in default watcher: {}", watchedEvent);
            if (watchedEvent.getType() == Event.EventType.None
                    && watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                startLatch.countDown();
                log.info("startLatch.countDown()");
            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged
                    || watchedEvent.getType() == Event.EventType.NodeDataChanged
                    || watchedEvent.getType() == Event.EventType.NodeCreated) {
                watcherTest.countDownCloseLatch();
                log.info("in default watcher watcherTest.countDownCloseLatch()");
            }
        }

        public void await() throws InterruptedException {
            startLatch.await();
        }
    }

    class ExistsWatcher implements Watcher {
        ZookeeperWatcherTest watcherTest;

        public ExistsWatcher(ZookeeperWatcherTest watcherTest) {
            this.watcherTest = watcherTest;
        }

        @Override
        public void process(WatchedEvent event) {
            log.info("event in exists watcher: {}", event);
            Event.EventType eventType = event.getType();
            log.info("event type: {}", eventType);
            if (eventType == Event.EventType.NodeCreated
                    || eventType == Event.EventType.NodeChildrenChanged
                    || eventType == Event.EventType.NodeDataChanged) {
                watcherTest.countDownCloseLatch();
                log.info("watcher test count down");
            }
        }
    }
}
