package com.backbase.accelerators.mx.nexus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SessionMember {

    @JsonProperty("aggregated_at")
    private String aggregatedAt;

    @JsonProperty("connection_status_message")
    private String connectionStatusMessage;

    @JsonProperty("external_guid")
    private String externalGuid;

    @JsonProperty("guid")
    private String guid;

    @JsonProperty("institution_code")
    private String institutionCode;

    @JsonProperty("institution_guid")
    private String institutionGuid;

    @JsonProperty("is_being_aggregated")
    private boolean isBeingAggregated;

    @JsonProperty("is_manual")
    private boolean isManual;

    @JsonProperty("is_managed_by_user")
    private boolean isManagedByUser;

    @JsonProperty("is_oauth")
    private boolean isOauth;

    @JsonProperty("metadata")
    private String metadata;

    @JsonProperty("most_recent_job_guid")
    private String mostRecentJobGuid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("needs_updated_credentials")
    private boolean needsUpdatedCredentials;

    @JsonProperty("revision")
    private Integer revision;

    @JsonProperty("successfully_aggregated_at")
    private String successfullyAggregatedAt;

    @JsonProperty("user_guid")
    private String userGuid;

    @JsonProperty("is_user_created")
    private boolean isUserCreated;

    @JsonProperty("connection_status_id")
    private Integer connectionStatusId;

    @JsonProperty("connection_status")
    private String connectionStatus;
}
