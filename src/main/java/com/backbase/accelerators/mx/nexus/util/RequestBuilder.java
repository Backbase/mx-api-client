package com.backbase.accelerators.mx.nexus.util;

import com.backbase.accelerators.mx.configuration.MxRequestSettings;
import com.backbase.accelerators.mx.nexus.model.AccountNickname;
import com.backbase.accelerators.mx.nexus.model.AccountVisibility;
import com.backbase.accelerators.mx.nexus.model.NexusSession;
import com.backbase.accelerators.mx.nexus.model.SessionRequest;
import com.backbase.accelerators.mx.nexus.model.UpdateAccountNicknameRequest;
import com.backbase.accelerators.mx.nexus.model.UpdateAccountVisibilityRequest;
import com.backbase.accelerators.mx.util.MxUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

import static com.backbase.accelerators.mx.nexus.util.Constants.ACCOUNTS;
import static com.backbase.accelerators.mx.nexus.util.Constants.ACCOUNT_NUMBERS;
import static com.backbase.accelerators.mx.nexus.util.Constants.APPLICATION_MX_NEXUS_V1_JSON;
import static com.backbase.accelerators.mx.nexus.util.Constants.MD_API_TOKEN_HEADER;
import static com.backbase.accelerators.mx.nexus.util.Constants.MD_SESSION_TOKEN_HEADER;
import static com.backbase.accelerators.mx.nexus.util.Constants.MEMBERS;
import static com.backbase.accelerators.mx.nexus.util.Constants.SESSIONS;
import static com.backbase.accelerators.mx.util.Constants.ACCEPT_HEADER;
import static com.backbase.accelerators.mx.util.Constants.CONTENT_TYPE_HEADER;
import static com.backbase.accelerators.mx.util.Constants.MD_API_KEY_HEADER;
import static java.net.http.HttpRequest.BodyPublishers.ofString;

@Slf4j
public class RequestBuilder {

    public static HttpRequest createGetSessionTokenRequest(MxRequestSettings mxRequestSettings, String mdApiToken) {
        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getNexusBaseUrl())
            .pathSegment(SESSIONS)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(ACCEPT_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .header(MD_API_TOKEN_HEADER, mdApiToken)
            .POST(ofString(MxUtils.toJsonString(buildSessionRequest())))
            .build();
    }

    public static HttpRequest createGetAccountNumbersRequest(
        MxRequestSettings mxRequestSettings,
        String mxSessionToken,
        String accountId) {

        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getNexusBaseUrl())
            .pathSegment(ACCOUNTS, accountId, ACCOUNT_NUMBERS)
            .pathSegment(SESSIONS)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(ACCEPT_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .header(MD_SESSION_TOKEN_HEADER, mxSessionToken)
            .GET()
            .build();
    }

    public static HttpRequest createGetSessionMemberRequest(
        MxRequestSettings mxRequestSettings,
        String mxSessionToken,
        String memberId) {

        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getNexusBaseUrl())
            .pathSegment(MEMBERS)
            .pathSegment(memberId)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(ACCEPT_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .header(MD_SESSION_TOKEN_HEADER, mxSessionToken)
            .GET()
            .build();
    }

    public static HttpRequest createGetSessionMembersRequest(
        MxRequestSettings mxRequestSettings,
        String mxSessionToken) {

        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getNexusBaseUrl())
            .pathSegment(MEMBERS)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(ACCEPT_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .header(MD_SESSION_TOKEN_HEADER, mxSessionToken)
            .GET()
            .build();
    }

    public static HttpRequest createGetMemberAccountNumberRequest(
        MxRequestSettings mxRequestSettings,
        String mxSessionToken,
        String memberId) {

        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getNexusBaseUrl())
            .pathSegment(MEMBERS)
            .pathSegment(memberId)
            .pathSegment(ACCOUNT_NUMBERS)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(ACCEPT_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .header(MD_SESSION_TOKEN_HEADER, mxSessionToken)
            .GET()
            .build();
    }

    public static HttpRequest createGetAccountDetailsRequest(
        MxRequestSettings mxRequestSettings,
        String mxSessionToken,
        String mxAccountGuid) {

        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getNexusBaseUrl())
            .pathSegment(ACCOUNTS)
            .pathSegment(mxAccountGuid)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(ACCEPT_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .header(MD_SESSION_TOKEN_HEADER, mxSessionToken)
            .GET()
            .build();
    }

    public static HttpRequest createUpdateAccountNicknameRequest(
        MxRequestSettings mxRequestSettings,
        String mxSessionToken,
        String mxAccountGuid,
        String nickname) {

        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getNexusBaseUrl())
            .pathSegment(ACCOUNTS)
            .pathSegment(mxAccountGuid)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(ACCEPT_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .header(MD_SESSION_TOKEN_HEADER, mxSessionToken)
            .PUT(ofString(MxUtils.toJsonString(buildUpdateAccountNicknameRequest(nickname))))
            .build();
    }

    public static HttpRequest createUpdateAccountVisibilityRequest(
        MxRequestSettings mxRequestSettings,
        String mxSessionToken,
        String mxAccountGuid,
        boolean isHidden) {

        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getNexusBaseUrl())
            .pathSegment(ACCOUNTS)
            .pathSegment(mxAccountGuid)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(ACCEPT_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .header(MD_SESSION_TOKEN_HEADER, mxSessionToken)
            .PUT(ofString(MxUtils.toJsonString(buildUpdateAccountVisibilityRequest(isHidden))))
            .build();
    }

    public static HttpRequest createDeleteAccountRequest(
        MxRequestSettings mxRequestSettings,
        String mxSessionToken,
        String mxMemberGuid) {

        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getNexusBaseUrl())
            .pathSegment(MEMBERS)
            .pathSegment(mxMemberGuid)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(ACCEPT_HEADER, APPLICATION_MX_NEXUS_V1_JSON)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .header(MD_SESSION_TOKEN_HEADER, mxSessionToken)
            .DELETE()
            .build();
    }

    private static SessionRequest buildSessionRequest() {
        val session = NexusSession.builder()
            .skipAggregation(true)
            .build();

        return SessionRequest.builder()
            .session(session)
            .build();
    }

    private static UpdateAccountNicknameRequest buildUpdateAccountNicknameRequest(String nickname) {
        val accountNickName = AccountNickname.builder()
            .nickname(nickname)
            .build();

        return UpdateAccountNicknameRequest.builder()
            .account(accountNickName)
            .build();
    }

    private static UpdateAccountVisibilityRequest buildUpdateAccountVisibilityRequest(boolean isHidden) {
        val account = AccountVisibility.builder()
            .isHidden(isHidden)
            .build();

        return UpdateAccountVisibilityRequest.builder()
            .account(account)
            .build();
    }
}
