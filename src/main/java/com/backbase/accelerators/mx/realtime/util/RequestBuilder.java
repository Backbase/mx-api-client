package com.backbase.accelerators.mx.realtime.util;

import com.backbase.accelerators.mx.configuration.MxRequestSettings;
import com.backbase.accelerators.mx.realtime.model.CreateUserRequestBody;
import com.backbase.accelerators.mx.realtime.model.MxRequestUser;
import com.backbase.accelerators.mx.util.MxUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpRequest;

import static com.backbase.accelerators.mx.realtime.util.Constants.APPLICATION_MX_MDX_V5_JSON;
import static com.backbase.accelerators.mx.realtime.util.Constants.USERS_JSON;
import static com.backbase.accelerators.mx.util.Constants.ACCEPT_HEADER;
import static com.backbase.accelerators.mx.util.Constants.CONTENT_TYPE_HEADER;
import static com.backbase.accelerators.mx.util.Constants.MD_API_KEY_HEADER;
import static java.net.http.HttpRequest.BodyPublishers.ofString;

@Slf4j
public class RequestBuilder {

    public static HttpRequest createCreateUserRequest(
        MxRequestSettings mxRequestSettings,
        String mxUserId) {

        val uri = UriComponentsBuilder.fromUriString(mxRequestSettings.getRealTimeBaseUrl())
            .pathSegment(mxRequestSettings.getClientId())
            .pathSegment(USERS_JSON)
            .build()
            .toUri();

        log.debug("Created request URI: {}", uri);
        return HttpRequest.newBuilder(uri)
            .header(CONTENT_TYPE_HEADER, APPLICATION_MX_MDX_V5_JSON)
            .header(ACCEPT_HEADER, APPLICATION_MX_MDX_V5_JSON)
            .header(MD_API_KEY_HEADER, mxRequestSettings.getMdApiKey())
            .POST(ofString(MxUtils.toJsonString(buildCreateUserRequestBody(mxUserId))))
            .build();
    }

    private static CreateUserRequestBody buildCreateUserRequestBody(String mxUserId) {
        val mxRequestUser = new MxRequestUser();
        mxRequestUser.setId(mxUserId);

        val createUserRequestBody = new CreateUserRequestBody();
        return createUserRequestBody.setUser(mxRequestUser);
    }

}
