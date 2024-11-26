package org.timeapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.timeapp.domain.Times;

@Service
public class KafkaSendMessageService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value(value = "${spring.kafka.producer.topic}")
    private String topic;

    @Autowired
    public KafkaSendMessageService(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTimes(Times times) {
        kafkaTemplate.send(topic, times);
    }
}
