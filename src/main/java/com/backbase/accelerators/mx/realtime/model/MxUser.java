package com.backbase.accelerators.mx.realtime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MxUser {

    private String id;

    private String birthdate;

    private String gender;

    @JsonProperty("logged_in_at")
    private String loggedInAt;

    @JsonProperty("credit_score")
    private String creditScore;

    private String email;

    @JsonProperty("first_name")
    private String firstName;

    private String guid;

    @JsonProperty("is_disabled")
    private boolean isDisabled;

    @JsonProperty("last_name")
    private String lastName;

    private String metadata;

    private String phone;

    @JsonProperty("zip_code")
    private String zipCode;
}
