package com.manueljuncal.wikimedia.infraestructure.api.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GoldDataResponse {

    private String date;
    private List<GoldPriceResponse> items;
}
