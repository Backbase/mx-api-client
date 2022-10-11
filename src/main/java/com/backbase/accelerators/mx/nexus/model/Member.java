package com.backbase.accelerators.mx.nexus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Member {

    @JsonProperty("aggregation_status")
    private long aggregationStatus;

    @JsonProperty("connection_status")
    private long connectionStatus;

    @JsonProperty("guid")
    private String guid;

    @JsonProperty("institution_guid")
    private String institutionGuid;

    @JsonProperty("institution_name")
    private String institutionName;

    @JsonProperty("is_being_aggregated")
    private boolean isBeingAggregated;

    @JsonProperty("is_manual")
    private boolean isManual;

    @JsonProperty("is_managed_by_user")
    private boolean isManagedByUser;

    @JsonProperty("is_oauth")
    private boolean isOauth;

    @JsonProperty("last_job_guid")
    private String lastJobGuid;

    @JsonProperty("last_job_status")
    private String lastJobStatus;

    @JsonProperty("last_update_time")
    private String lastUpdateTime;

    @JsonProperty("metadata")
    private String metadata;

    @JsonProperty("mfa")
    private String mfa;

    @JsonProperty("most_recent_job_guid")
    private String mostRecentJobGuid;

    @JsonProperty("needs_updated_credentials")
    private boolean needsUpdatedCredentials;

    @JsonProperty("name")
    private String name;

    @JsonProperty("process_status")
    private String processStatus;

    @JsonProperty("revision")
    private long revision;

    @JsonProperty("user_guid")
    private String userGuid;

    @JsonProperty("oauth_window_uri")
    private String oauthWindowUri;

    @JsonProperty("verification_is_enabled")
    private boolean verificationIsEnabled;

    @JsonProperty("successfully_aggregated_at")
    private long successfullyAggregatedAt;
}
