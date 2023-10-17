package com.manueljuncal.wikimedia.infraestructure.api.async;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WikimediaEvent {

    @JsonProperty("server_url")
    private String serverUrl;
    private String uri;
    private String title;
    @JsonProperty("server_name")
    private String serverName;
}
