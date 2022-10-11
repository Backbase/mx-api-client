package com.backbase.accelerators.mx.microdeposit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetAllMicroDepositsResponse {

    @JsonProperty("micro_deposits")
    private List<MicroDeposit> microDeposits;
}
