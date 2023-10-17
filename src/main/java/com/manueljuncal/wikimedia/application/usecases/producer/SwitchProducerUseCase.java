package com.manueljuncal.wikimedia.application.usecases.producer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SwitchProducerUseCase {

    private ProduceWikimediaEventUseCase produceWikimediaEventUseCase;

    public Boolean execute(final String action) {
        try {
            produceWikimediaEventUseCase.execute();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
