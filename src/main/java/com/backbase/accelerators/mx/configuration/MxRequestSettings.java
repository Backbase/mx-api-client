package com.backbase.accelerators.mx.configuration;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MxRequestSettings {

    private String ssoBaseUrl;
    private String realTimeBaseUrl;
    private String nexusBaseUrl;
    private String clientId;
    private String mdApiKey;
}
