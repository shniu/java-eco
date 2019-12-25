package io.github.shniu.arts.design.isp.config;

public class KafkaConfig implements Updater {
    private ConfigSource configSource; // 配置中心（比如zookeeper）
    private String address;
    private int timeout;
    private int maxTotal;

    // 省略其他配置: maxWaitMillis,maxIdle,minIdle...

    public KafkaConfig(ConfigSource configSource) {
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
    public void run() {
        // todo hot update
    }
}
