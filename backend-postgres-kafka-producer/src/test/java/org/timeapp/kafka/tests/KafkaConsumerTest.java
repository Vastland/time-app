//package org.timeapp.kafka.tests;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.CountDownLatch;
//
//@Component
//public class KafkaConsumerTest {
//
//
//
//    private CountDownLatch latch = new CountDownLatch(1);
//
//
//    private String payload;
//
//    @KafkaListener(topics =  "${spring.kafka.test.topic}")
//    public void receive(ConsumerRecord<?, ?> consumerRecord) {
//        payload = consumerRecord.toString();
//        latch.countDown();
//    }
//
//    public void resetLatch() {
//        latch = new CountDownLatch(1);
//    }
//
//    public CountDownLatch getLatch() {
//        return latch;
//    }
//
//    public String getPayload() {
//        return payload;
//    }
//
//
//    // other getters
//}
