package com.backbase.accelerators.mx.nexus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetMemberAccountNumbersResponse {

    @JsonProperty("account_numbers")
    private List<MemberAccountNumber> accountNumbers;
}
