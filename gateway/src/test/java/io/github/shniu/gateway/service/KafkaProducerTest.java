package io.github.shniu.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

import java.util.Properties;

@Slf4j
public class KafkaProducerTest {

    public static final String TOPIC_NAME = "test05";

    @Test
    public void testProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "ubuntu-xenial:9093");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put("retries", 3);
        props.put("batch.size", 16384);  // 16k
        props.put("linger.ms", 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); // 32m

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        TopicPartition tp;
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            for (int i = 0; i < 15; i++) {
                producer.send(new ProducerRecord<>(TOPIC_NAME, "kafka-" + i));
            }

            for (int i = 0; i < 15; i++) {
                producer.send(new ProducerRecord<>(TOPIC_NAME, "kafka callback-" + i), (metadata, exception) -> {
                    if (exception == null) {
                        log.info("record partition {} offset {}", metadata.partition(), metadata.offset());
                        log.info("record {}", metadata);
                    }
                });
            }
        }
    }
}
