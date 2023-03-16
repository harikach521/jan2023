package com.example.jan2023.kafka;

import com.example.jan2023.model.Emp;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerService {
    @KafkaListener(topics = "emp.topic", groupId = "user_group")
    public void consumeUser1(ConsumerRecord<String, Emp> consumerRecord){
        System.out.println("CONSUMER RECORD:" +consumerRecord.value());
    }
}
