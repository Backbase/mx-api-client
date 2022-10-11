package com.backbase.accelerators.mx.sso.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetMxApiTokenResponse {

    @JsonProperty("api_token")
    private ApiToken apiToken;
}
