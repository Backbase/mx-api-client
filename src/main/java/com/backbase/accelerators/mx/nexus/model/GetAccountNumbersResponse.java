package com.backbase.accelerators.mx.nexus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetAccountNumbersResponse {

    @JsonProperty("account_numbers")
    private List<AccountNumberItem> accountNumbers;
}
