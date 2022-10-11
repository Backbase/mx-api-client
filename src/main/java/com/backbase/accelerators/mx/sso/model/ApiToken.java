package com.backbase.accelerators.mx.sso.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApiToken {

    @JsonProperty("token")
    private String token;
}
