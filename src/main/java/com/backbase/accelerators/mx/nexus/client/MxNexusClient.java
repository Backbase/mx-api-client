package com.backbase.accelerators.mx.nexus.client;

import com.backbase.accelerators.mx.BaseClient;
import com.backbase.accelerators.mx.configuration.MxRequestSettings;
import com.backbase.accelerators.mx.nexus.model.GetAccountDetailsResponse;
import com.backbase.accelerators.mx.nexus.model.GetAccountNumbersResponse;
import com.backbase.accelerators.mx.nexus.model.GetMemberAccountNumbersResponse;
import com.backbase.accelerators.mx.nexus.model.GetSessionMemberResponse;
import com.backbase.accelerators.mx.nexus.model.GetSessionMembersResponse;
import com.backbase.accelerators.mx.nexus.model.GetSessionTokenResponse;
import com.backbase.accelerators.mx.nexus.model.UpdateAccountDetailsResponse;
import com.backbase.accelerators.mx.nexus.util.RequestBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.net.http.HttpClient;

@Slf4j
@RequiredArgsConstructor
public class MxNexusClient extends BaseClient {

    private final MxRequestSettings mxRequestSettings;
    private final HttpClient httpClient;

    public GetSessionTokenResponse getNexusSessionToken(String mdApiToken) {
        log.debug("Retrieving Nexus session token");

        return execute(
            httpClient,
            RequestBuilder.createGetSessionTokenRequest(mxRequestSettings, mdApiToken),
            GetSessionTokenResponse.class);
    }

    public GetAccountNumbersResponse getAccountNumbers(String mxSessionToken, String mxAccountId) {
        log.debug("Retrieving account numbers for mxAccountId: {}", mxAccountId);

        return execute(
            httpClient,
            RequestBuilder.createGetAccountNumbersRequest(mxRequestSettings, mxSessionToken, mxAccountId),
            GetAccountNumbersResponse.class);
    }

    public GetSessionMemberResponse getSessionMember(String mxSessionToken, String memberId) {
        log.debug("Retrieving session member with memberId {}", memberId);

        return execute(
            httpClient,
            RequestBuilder.createGetSessionMemberRequest(mxRequestSettings, mxSessionToken, memberId),
            GetSessionMemberResponse.class);
    }

    public GetSessionMembersResponse getSessionMembers(String mxSessionToken) {
        log.debug("Retrieving session members");

        return execute(
            httpClient,
            RequestBuilder.createGetSessionMembersRequest(mxRequestSettings, mxSessionToken),
            GetSessionMembersResponse.class);
    }

    public GetMemberAccountNumbersResponse getMemberAccountNumbers(String mxSessionToken, String mxMemberId) {
        log.debug("Retrieving member account numbers with mxMemberId: {}", mxMemberId);

        return execute(
            httpClient,
            RequestBuilder.createGetMemberAccountNumberRequest(mxRequestSettings, mxSessionToken, mxMemberId),
            GetMemberAccountNumbersResponse.class);
    }

    public GetAccountDetailsResponse getAccountDetails(String mxSessionToken, String mxAccountGuid) {
        log.debug("Retrieving account details with mxAccountGuid: {}", mxAccountGuid);

        return execute(
            httpClient,
            RequestBuilder.createGetAccountDetailsRequest(mxRequestSettings, mxSessionToken, mxAccountGuid),
            GetAccountDetailsResponse.class);
    }

    public UpdateAccountDetailsResponse updateAccountNickname(
        String mxSessionToken,
        String mxAccountGuid,
        String nickname) {

        log.debug("Updating account nickname for given mxAccountGuid: {} with nickname: {}", mxAccountGuid, nickname);
        val httpRequest = RequestBuilder.createUpdateAccountNicknameRequest(
            mxRequestSettings,
            mxSessionToken,
            mxAccountGuid,
            nickname);

        return execute(
            httpClient,
            httpRequest,
            UpdateAccountDetailsResponse.class);
    }

    public UpdateAccountDetailsResponse updateAccountVisibility(
        String mxSessionToken,
        String mxAccountGuid,
        boolean isHidden) {

        log.debug("Updating account visibility for mxAccountGuid: {} with value isHidden={}", mxAccountGuid, isHidden);
        val httpRequest = RequestBuilder.createUpdateAccountVisibilityRequest(
            mxRequestSettings,
            mxSessionToken,
            mxAccountGuid,
            isHidden);

        return execute(
            httpClient,
            httpRequest,
            UpdateAccountDetailsResponse.class);
    }

    public void deleteMemberAccount(String mxSessionToken, String mxMemberGuid) {
        log.debug("Deleting account for mxMemberGuid: {}", mxMemberGuid);
        execute(httpClient, RequestBuilder.createDeleteAccountRequest(mxRequestSettings, mxSessionToken, mxMemberGuid));
    }
}
