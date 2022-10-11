package com.backbase.accelerators.mx.microdeposit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MicroDepositsAccountResponse {

    @JsonProperty("micro_deposit")
    private MicroDeposit microDeposit;
}
