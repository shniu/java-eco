package io.github.shniu.arts.design.isp.config;

public class Application {
    static ConfigSource configSource = new ZookeeperConfigSource();
    private static final RedisConfig redisConfig = new RedisConfig(configSource);
    private static final MySQLConfig mysqlConfig = new MySQLConfig(configSource);
    private static final KafkaConfig kafkaConfig = new KafkaConfig(configSource);

    public static void main(String[] args) {

        // hot update
        ScheduledUpdater redisUpdater = new ScheduledUpdater(redisConfig, 300, 300);
        redisUpdater.run();

        ScheduledUpdater kafkaUpdater = new ScheduledUpdater(kafkaConfig, 100, 100);
        kafkaUpdater.run();

        // viewer
        SimpleHttpServer simpleHttpServer = new SimpleHttpServer("127.0.0.1", 9000);
        simpleHttpServer.addViewer("/config", redisConfig);
        simpleHttpServer.addViewer("/config", mysqlConfig);
        simpleHttpServer.addViewer("/metrics", new ApiMetrics());
        simpleHttpServer.addViewer("/metrics", new DbMetrics());
        simpleHttpServer.run();
    }
}
