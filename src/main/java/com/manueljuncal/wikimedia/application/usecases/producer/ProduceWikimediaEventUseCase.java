package com.manueljuncal.wikimedia.application.usecases.producer;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ProduceWikimediaEventUseCase {

    @Value("${spring.kafka.producer.source-url}")
    private String wikimediaUrl;
    @Value("${spring.kafka.producer.topic}")
    private String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void execute() throws InterruptedException {
        BackgroundEventHandler eventHandler = new WikimediaProducerHandler(kafkaTemplate, topic);
        BackgroundEventSource backgroundEventSource = new BackgroundEventSource.Builder(eventHandler,
                new EventSource.Builder(URI.create(wikimediaUrl))
        ).build();
        
        backgroundEventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
