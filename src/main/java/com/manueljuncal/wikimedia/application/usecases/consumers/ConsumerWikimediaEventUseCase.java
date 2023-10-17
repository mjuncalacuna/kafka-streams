package com.manueljuncal.wikimedia.application.usecases.consumers;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ConsumerWikimediaEventUseCase {
    
    
    @KafkaListener(topics = "${spring.kafka.producer.topic}", groupId = "wikimedia-listener")
    public void execute(String message) {
        System.out.printf("Received message: %s%n", message);
        /*KafkaConsumer<String, WikimediaEvent> wikimediaConsumer = new KafkaConsumer<>(consumerFactory
                .getConfigurationProperties());
        
        wikimediaConsumer.subscribe(Collections.singletonList(topic));
        
        while(true) {
            ConsumerRecords<String, WikimediaEvent> wikimediaEvents = wikimediaConsumer.poll(Duration.ofMillis(10000));
            wikimediaEvents.forEach(event -> System.out.println(event.toString()));
        }*/
    }
}
