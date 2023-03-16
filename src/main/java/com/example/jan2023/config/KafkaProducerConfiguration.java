package com.example.jan2023.config;

import com.example.jan2023.model.Emp;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    //connection values
    //connection factory which is main pobejct that create connections
    @Bean
    public ProducerFactory<String, Emp> producerFactory(){
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ProducerConfig.ACKS_CONFIG, "1");
        config.put(ProducerConfig.BATCH_SIZE_CONFIG, 25);
        return new DefaultKafkaProducerFactory<>(config);
    }

    //give us kafka bean which has connec obj already we just need to use kafka
    // template an dsend our message
    @Bean
    public KafkaTemplate<String, Emp> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
