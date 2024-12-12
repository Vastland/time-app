package org.timeapp.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.timeapp.domain.Times;
import org.timeapp.service.TimesService;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private static final String TOPIC_TIME = "${spring.kafka.topic}";

    private static final String KAFKA_CONSUMER_GROUP_ID = "${spring.kafka.consumer.groupId}";

    private final TimesService timesService;

    @Autowired
    public KafkaConsumerConfig(TimesService timesService) {
        this.timesService = timesService;
    }

    @KafkaListener(topics = TOPIC_TIME, groupId = KAFKA_CONSUMER_GROUP_ID)
    public void listenGroupTime(String message) throws Exception {
        System.out.println("Received Message in group time: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        Times times = objectMapper.readValue(message, Times.class);
        timesService.saveTimes(times);
    }
}
