package com.backbase.accelerators.mx;

import com.backbase.accelerators.mx.util.MxUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class BaseClient {

    @SneakyThrows
    public <T> T execute(HttpClient httpClient, HttpRequest httpRequest, Class<T> responseType) {
        val httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        val responseString = httpResponse.body();
        log.debug("MX API response - statusCode: {}; body: {}", httpResponse.statusCode(), responseString);

        MxUtils.checkForErrors(httpResponse);
        return MxUtils.toPojo(responseString, responseType);
    }

    @SneakyThrows
    public void execute(HttpClient httpClient, HttpRequest httpRequest) {
        val httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        val responseString = httpResponse.body();
        log.debug("MX API response - statusCode: {}; body: {}", httpResponse.statusCode(), responseString);

        MxUtils.checkForErrors(httpResponse);
    }
}
