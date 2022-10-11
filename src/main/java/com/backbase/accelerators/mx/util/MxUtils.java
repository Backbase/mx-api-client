package com.backbase.accelerators.mx.util;

import com.backbase.accelerators.mx.exception.MxApiClientException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.net.http.HttpResponse;
import java.util.Set;
import java.util.StringJoiner;

public class MxUtils {

    public static final String BB_PREFIX = "BB";
    public static final String DELIMITER = "_";

    public static String buildMxUserId(String environment, String serviceAgreementExternalId, String userInternalId) {
        return new StringJoiner(DELIMITER)
                .add(BB_PREFIX)
                .add(environment)
                .add(buildHashedUserId(serviceAgreementExternalId, userInternalId))
                .toString();
    }

    private static String buildHashedUserId(String saExternalId, String userInternalId) {
        return String.valueOf((saExternalId + userInternalId).hashCode());
    }

    public static void checkForErrors(HttpResponse<String> httpResponse) {
        final Set<Integer> successCodes = Set.of(200, 201, 202, 204);

        if (!successCodes.contains(httpResponse.statusCode())) {
            var mxApiClientException = new MxApiClientException(httpResponse.body());
            mxApiClientException.setStatusCode(httpResponse.statusCode());

            throw mxApiClientException;
        }
    }

    @SneakyThrows
    public static <T> T toPojo(String responseString, Class<T> clazz) {
        return new ObjectMapper().readValue(responseString, clazz);
    }

    @SneakyThrows
    public static String toJsonString(Object object) {
        return new ObjectMapper().writeValueAsString(object);
    }
}
