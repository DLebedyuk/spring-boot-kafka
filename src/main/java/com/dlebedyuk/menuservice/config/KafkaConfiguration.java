package com.dlebedyuk.menuservice.config;

import com.dlebedyuk.menuservice.model.Dish;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@Slf4j
@Configuration
@EnableKafka
public class KafkaConfiguration {

//    @Bean
//    public KafkaOperations<String, Object> getEventKafkaTemplate() { // producer to DLQ
//        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfigs()));
//    }
//
//    @Bean
//    public CommonErrorHandler errorHandler(KafkaOperations<Object, Object> template) {
//        return new DefaultErrorHandler(
//                new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L, 2));
//    }

    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

    @KafkaListener(id = "fooGroup", topics = "topic1")
    public void listen(Dish dish) {
        log.info("Received: " + dish);
        if (dish.getName().startsWith("fail")) {
            throw new RuntimeException("failed");
        }
        log.info("Test topic 1...");
    }

    @KafkaListener(id = "dltGroup", topics = "topic1.DLT")
    public void dltListen(byte[] in) {
        log.info("Received from DLT: " + new String(in));
        log.info("Test topic dlt...");
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic("topic1", 1, (short) 1);
    }

    @Bean
    public NewTopic dlt() {
        return new NewTopic("topic1.DLT", 1, (short) 1);
    }
}
