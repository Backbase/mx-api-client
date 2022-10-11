package com.backbase.acclerators.mx.realtime.client

import com.backbase.accelerators.mx.configuration.MxRequestSettings
import com.backbase.accelerators.mx.realtime.client.MxRealTimeClient
import com.backbase.accelerators.mx.realtime.model.CreateUserResponseBody
import com.backbase.acclerators.mx.TestData
import com.backbase.acclerators.mx.TestUtils
import spock.lang.Specification

import java.net.http.HttpClient
import java.net.http.HttpResponse

class MxRealTimeClientSpec extends Specification {

    private MxRequestSettings mxRequestSettings = Mock()
    private HttpClient httpClient = Mock()

    private MxRealTimeClient mxRealTimeClient = new MxRealTimeClient(mxRequestSettings, httpClient)


    void 'createUser creates a new user in MX'() {
        given:
        String mxUserId = 'BB_DEV_-107980962'

        when:
        CreateUserResponseBody result = mxRealTimeClient.createUser(mxUserId)

        then:
        1 * mxRequestSettings.realTimeBaseUrl >> 'https://int-live.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'
        1 * mxRequestSettings.clientId >> 'fakeClientId'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> TestUtils.toJsonString(TestData.createUserResponseBody)
                }
        }

        and:
        verifyAll(result.user) {
            id == 'BB_DEV_-107980962'
            guid == 'USR-9ec1871b-b810-4c84-98d5-b93086c867db'
            !isDisabled
        }
    }
}
