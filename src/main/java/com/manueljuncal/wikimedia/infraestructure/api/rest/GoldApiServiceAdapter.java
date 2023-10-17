package com.manueljuncal.wikimedia.infraestructure.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manueljuncal.wikimedia.infraestructure.api.rest.model.GoldDataResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class GoldApiServiceAdapter {
    
    @Value("${rapid-api.X-RapidAPI-KEY}")
    private String rapidAPIKey;
    @Value("${rapid-api.X-RapidAPI-HOST}")
    private String rapidAPIHost;
    @Value("${rapid-api.endpoints.get-gold}")
    private String getGoldEndpoint;

    @SneakyThrows
    @Scheduled(cron = "*/20 * * * * *")
    public void execute() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://gold-price6.p.rapidapi.com/GetGold?CUR=EUR")
                .get()
                .addHeader("X-RapidAPI-Key", "050015dc63mshfcac49c997e32e5p1a87e3jsn2e95f84c5e66")
                .addHeader("X-RapidAPI-Host", "gold-price6.p.rapidapi.com")
                .build();
        Response response = client.newCall(request).execute();

        ObjectMapper mapper = new ObjectMapper();
        GoldDataResponse goldDataResponse = mapper.readValue(Objects.requireNonNull(response.body()).string(), GoldDataResponse.class);

        System.out.println("GOLD PRICE: " + goldDataResponse.getItems().get(0).getXauPrice().toString());
    }
}
