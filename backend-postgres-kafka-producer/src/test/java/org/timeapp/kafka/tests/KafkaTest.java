package org.timeapp.kafka.tests;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.timeapp.domain.Times;
import org.timeapp.kafka.KafkaProducerConfig;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@EmbeddedKafka(
        partitions = 1,
        topics = {"timeTopic"},
        brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "log.dir=target/embedded-kafka-logs"}
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@SpringBootTest
@Import(KafkaProducerConfig.class)
@ActiveProfiles("test")
class KafkaTest {

    private static final String TOPIC = "timeTopic";

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${spring.embedded.kafka.brokers}")
    private String embeddedKafkaBrokers;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    @Autowired
//    private KafkaSendMessageService kafkaSendMessageService;

    @Test
    void testProducerAndConsumer() {

        Times times = new Times();
        times.setTime("15:19:00");
        times.setCreatedAt(Instant.now());
//        kafkaSendMessageService.sendTimes(times);
        String test = "test";
        kafkaTemplate.send(TOPIC, test);

        Map<String, Object> consumerProps = new HashMap<>();

//        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafkaBrokers);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "times-1");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        consumerProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Times.class);
//        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafkaBrokers);

        KafkaConsumer<String, Times> consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(List.of(TOPIC));
//        ConsumerRecord<String, Times> received = KafkaTestUtils.getSingleRecord(consumer, TOPIC);

        boolean recordFound = false;
        for (int i = 0; i < 5; i++) {  // Максимум 5 спроб
            ConsumerRecords<String, Times> records = consumer.poll(Duration.ofMillis(1000));
            if (!records.isEmpty()) {
                for (ConsumerRecord<String, Times> record : records) {
                    assertEquals(times, record.value());
                    recordFound = true;
                }
                break;
            }
        }

        assertTrue(recordFound, "Повідомлення не знайдено в топіку!");

//        assertEquals(times, received.value());
    }
}
//@SpringBootTest
//@DirtiesContext
//@ActiveProfiles("test")
//@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
//class EmbeddedKafkaIntegrationTest {
//
//    @Autowired
//    private KafkaConsumerTest consumer;
//
//    @Autowired
//    private KafkaProducerConfig producer;
//
//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate;
//
//    @Value("${spring.kafka.test.topic}")
//    private String topic;
//
//    @Test
//    public void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived()
//            throws Exception {
//        String data = "Sending with our own simple KafkaProducer";
//
//        kafkaTemplate.send(topic, data);
//
//        boolean messageConsumed = consumer.getLatch().await(10, TimeUnit.SECONDS);
//        assertTrue(messageConsumed);
////        assertThat(consumer.getPayload(), containsString(data));
//    }
//}

