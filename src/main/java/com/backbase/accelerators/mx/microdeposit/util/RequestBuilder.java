package com.backbase.accelerators.mx.microdeposit.util;

import com.backbase.accelerators.mx.configuration.MxRequestSettings;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

import static com.backbase.accelerators.mx.microdeposit.util.Constants.APPLICATION_MX_MICRO_DEPOSIT_ACCEPT_HEADER;
import static com.backbase.accelerators.mx.microdeposit.util.Constants.APPLICATION_MX_MICRO_DEPOSIT_CONTENT_HEADER;
import static com.backbase.accelerators.mx.microdeposit.util.Constants.MICRO_DEPOSITS;
import static com.backbase.accelerators.mx.nexus.util.Constants.MD_SESSION_TOKEN_HEADER;
import static com.backbase.accelerators.mx.util.Constants.ACCEPT_HEADER;
import static com.backbase.accelerators.mx.util.Constants.CONTENT_TYPE_HEADER;
import static com.backbase.accelerators.mx.util.Constants.MD_API_KEY_HEADER;

@Slf4j
public class RequestBuilder {

    public static HttpRequest createMicroDepositAccountInfoRequest(
        MxRequestSettings mxRequestSettings,
        String mxSessionToken,
        String microDepositId) {

        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getNexusBaseUrl())
            .pathSegment(MICRO_DEPOSITS, microDepositId)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_MICRO_DEPOSIT_CONTENT_HEADER)
            .header(ACCEPT_HEADER, APPLICATION_MX_MICRO_DEPOSIT_ACCEPT_HEADER)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .header(MD_SESSION_TOKEN_HEADER, mxSessionToken)
            .GET()
            .build();
    }

    public static HttpRequest createGetMicroDepositsRequest(
        MxRequestSettings mxRequestSettings,
        String mxSessionToken) {

        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getNexusBaseUrl())
            .pathSegment(MICRO_DEPOSITS)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_MICRO_DEPOSIT_CONTENT_HEADER)
            .header(ACCEPT_HEADER, APPLICATION_MX_MICRO_DEPOSIT_ACCEPT_HEADER)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .header(MD_SESSION_TOKEN_HEADER, mxSessionToken)
            .GET()
            .build();
    }
}
