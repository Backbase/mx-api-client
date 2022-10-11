package com.backbase.accelerators.mx.nexus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NexusSession {

    @JsonProperty("skip_aggregation")
    private boolean skipAggregation;
}
