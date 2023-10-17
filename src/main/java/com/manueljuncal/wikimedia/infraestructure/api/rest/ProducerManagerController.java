package com.manueljuncal.wikimedia.infraestructure.api.rest;

import com.example.kafkastreams.controllers.ProducerManagerApi;
import com.example.kafkastreams.core.ProducerToggle200Response;
import com.manueljuncal.wikimedia.application.usecases.producer.SwitchProducerUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProducerManagerController implements ProducerManagerApi {

    private SwitchProducerUseCase switchProducerUseCase;

    @Override
    public ResponseEntity<ProducerToggle200Response> producerToggle(String action) {
        switchProducerUseCase.execute(action);
        return ResponseEntity.ok(new ProducerToggle200Response().status("200"));
    }

}
