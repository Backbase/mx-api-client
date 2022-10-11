package com.backbase.accelerators.mx.microdeposit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MicroDeposit {

    @JsonProperty("account_type")
    private long accountType;

    @JsonProperty("account_type_name")
    private String accountTypeName;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("guid")
    private String guid;

    @JsonProperty("institution_name")
    private String institutionName;

    @JsonProperty("routing_number")
    private String routingNumber;

    @JsonProperty("status")
    private long status;

    @JsonProperty("status_name")
    private String statusName;

    @JsonProperty("updated_at")
    private String updatedAt;
}
