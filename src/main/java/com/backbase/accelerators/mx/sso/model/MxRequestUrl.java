package com.backbase.accelerators.mx.sso.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@Accessors(chain = true)
@JsonInclude(Include.NON_NULL)
public class MxRequestUrl {

    private String type;

    private String mode;

    @JsonProperty("current_microdeposit_guid")
    private String currentMicroDepositGuid;

    @JsonProperty("wait_for_full_aggregation")
    private boolean waitForFullAggregation;

    @JsonProperty("ui_message_version")
    private String uiMessageVersion;

    @JsonProperty("is_mobile_webview")
    private boolean isMobileWebView;

    @JsonProperty("ui_message_webview_url_scheme")
    private String uiMessageWebViewUrlScheme;
}
