package com.manueljuncal.wikimedia.application.usecases.producer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.manueljuncal.wikimedia.infraestructure.api.async.WikimediaEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@AllArgsConstructor
public class WikimediaProducerHandler implements BackgroundEventHandler {

    private KafkaTemplate<String, String> kafkaTemplate;
    private String topic;

    @Override
    public void onOpen() {}

    @Override
    public void onClosed() {}

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        log.info(messageEvent.getData());
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        WikimediaEvent eventData = objectMapper.readValue(messageEvent.getData(), WikimediaEvent.class);

        kafkaTemplate.send(new ProducerRecord<>(topic, eventData.getServerUrl(), eventData.toString()));
    }

    @Override
    public void onComment(String s) {}

    @Override
    public void onError(Throwable throwable) {}
}
