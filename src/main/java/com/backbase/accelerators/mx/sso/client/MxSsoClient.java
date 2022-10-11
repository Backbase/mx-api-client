package com.backbase.accelerators.mx.sso.client;

import com.backbase.accelerators.mx.BaseClient;
import com.backbase.accelerators.mx.configuration.MxRequestSettings;
import com.backbase.accelerators.mx.sso.model.ConnectWidgetUrlResponseBody;
import com.backbase.accelerators.mx.sso.model.GetMxApiTokenResponse;
import com.backbase.accelerators.mx.sso.util.RequestBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.http.HttpClient;

@Slf4j
@RequiredArgsConstructor
public class MxSsoClient extends BaseClient {

    private final MxRequestSettings mxRequestSettings;
    private final HttpClient httpClient;

    public ConnectWidgetUrlResponseBody getConnectWidgetUrl(
        String mxUserId,
        String microDepositGuid,
        boolean isMobileRequest) {

        log.debug("Retrieving connect widget URL for user={}; microDepositGuid={}; isMobileRequest={}",
            mxUserId,
            microDepositGuid,
            isMobileRequest);

        var httpRequest =  RequestBuilder.createGetConnectWidgetUrlRequest(
            mxRequestSettings,
            mxUserId,
            microDepositGuid,
            isMobileRequest);

        return execute(
            httpClient,
            httpRequest,
            ConnectWidgetUrlResponseBody.class);
    }

    public GetMxApiTokenResponse getMxApiToken(String mxUserId) {
        log.debug("Obtaining MX API token for user: {}", mxUserId);

        return execute(
            httpClient,
            RequestBuilder.createGetMxApiTokenRequest(mxRequestSettings, mxUserId),
            GetMxApiTokenResponse.class);
    }
}
