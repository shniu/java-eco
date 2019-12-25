package io.github.shniu.arts.design.isp.config;

import java.util.Map;

public class MySQLConfig implements Viewer {
    private ConfigSource configSource; // 配置中心（比如zookeeper）
    private String address;
    private int timeout;
    private int maxTotal;

    // 省略其他配置: maxWaitMillis,maxIdle,minIdle...

    public MySQLConfig(ConfigSource configSource) {
        this.configSource = configSource;
    }

    public String getAddress() {
        return this.address;
    }

    // ...省略其他get()、init()方法...

    public void update() {
        // 从configSource加载配置到address/timeout/maxTotal...
    }

    @Override
    public String outputInPlainText() {
        // todo
        return null;
    }

    @Override
    public Map<String, String> output() {
        // todo
        return null;
    }
}
