package com.backbase.accelerators.mx.microdeposit.client;

import com.backbase.accelerators.mx.BaseClient;
import com.backbase.accelerators.mx.configuration.MxRequestSettings;
import com.backbase.accelerators.mx.microdeposit.model.GetAllMicroDepositsResponse;
import com.backbase.accelerators.mx.microdeposit.model.MicroDepositsAccountResponse;
import com.backbase.accelerators.mx.microdeposit.util.RequestBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.http.HttpClient;

@Slf4j
@RequiredArgsConstructor
public class MxMicroDepositClient extends BaseClient {

    private final MxRequestSettings mxRequestSettings;
    private final HttpClient httpClient;

    public MicroDepositsAccountResponse getMicroDepositDetails(String mxSessionToken, String microDepositId) {
        log.debug("Getting micro deposit account information by microDepositId: {}", microDepositId);

        return execute(
            httpClient,
            RequestBuilder.createMicroDepositAccountInfoRequest(mxRequestSettings, microDepositId, mxSessionToken),
            MicroDepositsAccountResponse.class);
    }

    public GetAllMicroDepositsResponse getAllMicroDeposits(String mxSessionToken) {
        log.debug("Retrieving all micro deposits for mxSessionToken: {}", mxSessionToken);

        return execute(
            httpClient,
            RequestBuilder.createGetMicroDepositsRequest(mxRequestSettings, mxSessionToken),
            GetAllMicroDepositsResponse.class);
    }
}
