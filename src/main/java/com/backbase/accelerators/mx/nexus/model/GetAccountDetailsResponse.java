package com.backbase.accelerators.mx.nexus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetAccountDetailsResponse {

    @JsonProperty("account")
    private AccountDetail account;
}
