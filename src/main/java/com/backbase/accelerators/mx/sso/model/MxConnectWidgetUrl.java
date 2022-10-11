package com.backbase.accelerators.mx.sso.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MxConnectWidgetUrl {

    private String url;
    private String type;

    @JsonProperty("user_id")
    private String userId;
}
