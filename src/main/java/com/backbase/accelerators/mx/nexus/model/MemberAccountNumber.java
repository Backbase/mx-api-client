package com.backbase.accelerators.mx.nexus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MemberAccountNumber {

    @JsonProperty("guid")
    private String guid;

    @JsonProperty("user_guid")
    private String userGuid;

    @JsonProperty("member_guid")
    private String memberGuid;

    @JsonProperty("account_guid")
    private String accountGuid;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("routing_number")
    private String routingNumber;

    @JsonProperty("transit_number")
    private String transitNumber;

    @JsonProperty("institution_number")
    private String institutionNumber;
}
