package io.github.shniu.gateway.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
public class KafkaConsumerTest {

    @Test
    public void testConsumer() {
        // 配置
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "ubuntu-xenial:9093");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "unitTest");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        // Consumer 实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // 订阅Topic
        consumer.subscribe(Arrays.asList("test05", "test06"));

        // 拉取数据
        for (; ; ) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.of(100, ChronoUnit.MILLIS));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                log.info("{} -- {} -- {} -- {}", consumerRecord.topic(),
                        consumerRecord.partition(), consumerRecord.key(), consumerRecord.value());
            }
        }
    }
}
