package com.backbase.accelerators.mx.realtime.client;

import com.backbase.accelerators.mx.BaseClient;
import com.backbase.accelerators.mx.configuration.MxRequestSettings;
import com.backbase.accelerators.mx.realtime.model.CreateUserResponseBody;
import com.backbase.accelerators.mx.realtime.util.RequestBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.http.HttpClient;

@Slf4j
@RequiredArgsConstructor
public class MxRealTimeClient extends BaseClient {

    private final MxRequestSettings mxRequestSettings;
    private final HttpClient httpClient;

    public CreateUserResponseBody createUser(String mxUserId) {
        log.debug("Creating MX user with mxUserId: {}", mxUserId);

        return execute(
            httpClient,
            RequestBuilder.createCreateUserRequest(mxRequestSettings, mxUserId),
            CreateUserResponseBody.class);
    }
}
