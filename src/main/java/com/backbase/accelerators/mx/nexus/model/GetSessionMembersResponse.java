package com.backbase.accelerators.mx.nexus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetSessionMembersResponse {

    @JsonProperty("members")
    private List<SessionMember> members;
}
