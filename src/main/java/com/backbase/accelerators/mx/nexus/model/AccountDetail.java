package com.backbase.accelerators.mx.nexus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountDetail {

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("account_type")
    private Integer accountType;

    @JsonProperty("account_type_name")
    private String accountTypeName;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("guid")
    private String guid;

    @JsonProperty("is_closed")
    private boolean isClosed;

    @JsonProperty("is_hidden")
    private boolean isHidden;

    @JsonProperty("member_guid")
    private String memberGuid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("routing_number")
    private String routingNumber;
}
