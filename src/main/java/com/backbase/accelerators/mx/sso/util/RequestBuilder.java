package com.backbase.accelerators.mx.sso.util;

import com.backbase.accelerators.mx.configuration.MxRequestSettings;
import com.backbase.accelerators.mx.sso.model.ConnectWidgetUrlRequestBody;
import com.backbase.accelerators.mx.sso.model.MxRequestUrl;
import com.backbase.accelerators.mx.util.MxUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

import static com.backbase.accelerators.mx.sso.util.Constants.API_TOKEN_URL_JSON;
import static com.backbase.accelerators.mx.sso.util.Constants.APPLICATION_MX_SSO_V3_JSON;
import static com.backbase.accelerators.mx.sso.util.Constants.URLS_JSON;
import static com.backbase.accelerators.mx.sso.util.Constants.USERS;
import static com.backbase.accelerators.mx.util.Constants.ACCEPT_HEADER;
import static com.backbase.accelerators.mx.util.Constants.CONTENT_TYPE_HEADER;
import static com.backbase.accelerators.mx.util.Constants.MD_API_KEY_HEADER;
import static java.net.http.HttpRequest.BodyPublishers.ofString;

@Slf4j
public class RequestBuilder {

    public static HttpRequest createGetConnectWidgetUrlRequest(
        MxRequestSettings mxRequestSettings,
        String mxUserId,
        String microDepositGuid,
        boolean isMobileRequest) {

        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getSsoBaseUrl())
            .pathSegment(mxRequestSettings.getClientId())
            .pathSegment(USERS)
            .pathSegment(mxUserId)
            .pathSegment(URLS_JSON)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_SSO_V3_JSON)
            .header(ACCEPT_HEADER, APPLICATION_MX_SSO_V3_JSON)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .POST(ofString(MxUtils.toJsonString(createConnectWidgetUrlRequestBody(microDepositGuid, isMobileRequest))))
            .build();
    }

    public static HttpRequest createGetMxApiTokenRequest(MxRequestSettings mxRequestSettings, String mxUserId) {
        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getSsoBaseUrl())
            .pathSegment(mxRequestSettings.getClientId())
            .pathSegment(USERS)
            .pathSegment(mxUserId)
            .pathSegment(API_TOKEN_URL_JSON)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_SSO_V3_JSON)
            .header(ACCEPT_HEADER, APPLICATION_MX_SSO_V3_JSON)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .GET()
            .build();
    }

    private static ConnectWidgetUrlRequestBody createConnectWidgetUrlRequestBody(
        String microDepositGuid,
        boolean isMobileRequest) {

        val connectWidgetUrlRequestBody = new ConnectWidgetUrlRequestBody();
        connectWidgetUrlRequestBody.setUrl(createMxRequestUrlBody(microDepositGuid, isMobileRequest));

        log.debug("Created ConnectWidgetUrlRequestBody: {}", connectWidgetUrlRequestBody);
        return connectWidgetUrlRequestBody;
    }

    private static MxRequestUrl createMxRequestUrlBody(String microDepositGuid, boolean isMobileRequest) {
        if (isMobileRequest) {
            log.debug("Mobile request detected...");
            return new MxRequestUrl()
                .setCurrentMicroDepositGuid(microDepositGuid)
                .setType("connect_widget")
                .setMode("verification")
                .setWaitForFullAggregation(false)
                .setUiMessageVersion("4")
                .setMobileWebView(true)
                .setUiMessageWebViewUrlScheme("TBD");
        }

        return new MxRequestUrl()
            .setCurrentMicroDepositGuid(microDepositGuid)
            .setType("connect_widget")
            .setMode("verification")
            .setWaitForFullAggregation(false)
            .setUiMessageVersion("4");
    }
}
