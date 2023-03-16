package com.example.jan2023.kafka;

import com.example.jan2023.model.Emp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaProducerService {

    //@Value("${kafka.topic.name}")
    private static String topicName = "emp.topic";

    @Autowired
    KafkaTemplate<String, Emp> kafkaTemplate;

    public String sendSimpleMessage(Emp emp) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message<Emp> message = MessageBuilder
                .withPayload(emp)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        ListenableFuture<SendResult<String, Emp>> future = kafkaTemplate.send(message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Emp>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + emp.getEmpno() + "] due to");
            }

            @Override
            public void onSuccess(SendResult<String, Emp> result) {
                System.out.println("Sent message=[" + emp.getEmpno() + "] with offset=[" +result.getRecordMetadata().offset() + "]");
            }
        });
        return "Success";
    }

}
