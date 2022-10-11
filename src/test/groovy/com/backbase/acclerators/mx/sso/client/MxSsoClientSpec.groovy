package com.backbase.acclerators.mx.sso.client

import com.backbase.accelerators.mx.configuration.MxRequestSettings
import com.backbase.accelerators.mx.sso.client.MxSsoClient
import com.backbase.accelerators.mx.sso.model.ConnectWidgetUrlResponseBody
import com.backbase.accelerators.mx.sso.model.GetMxApiTokenResponse
import com.backbase.acclerators.mx.TestData
import com.backbase.acclerators.mx.TestUtils
import spock.lang.Specification

import java.net.http.HttpClient
import java.net.http.HttpResponse

class MxSsoClientSpec extends Specification {

    private MxRequestSettings mxRequestSettings = Mock()
    private HttpClient httpClient = Mock()

    private MxSsoClient mxSsoClient = new MxSsoClient(mxRequestSettings, httpClient)

    void 'getConnectWidgetUrl returns a url for accessing MX connected accounts'() {
        given:
        String mxUserId = 'fakeUserId'
        String microDepositGuid = 'fakeGuid'
        boolean isMobileRequest = false

        when:
        ConnectWidgetUrlResponseBody result = mxSsoClient.getConnectWidgetUrl(mxUserId, microDepositGuid, isMobileRequest)

        then:
        1 * mxRequestSettings.ssoBaseUrl >> 'https://int-sso.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'
        1 * mxRequestSettings.clientId >> 'fakeClientId'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> TestUtils.toJsonString(TestData.connectWidgetUrlResponseBody)
                }
        }

        and:
        verifyAll(result) {
            url.url == 'https://int-widgets.moneydesktop.com/md/connect/wtf971...%3D'
            url.userId == 'BB_DEV_-107980962'
            url.type == 'connect_widget'
        }
    }

    void 'getMxApiToken returns a token for the given userId'() {
        given:
        String mxUserId = 'fakeUserId'

        when:
        GetMxApiTokenResponse result = mxSsoClient.getMxApiToken(mxUserId)

        then:
        1 * mxRequestSettings.ssoBaseUrl >> 'https://int-sso.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'
        1 * mxRequestSettings.clientId >> 'fakeClientId'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> TestUtils.toJsonString(TestData.getMxApiTokenResponse)
                }
        }

        and:
        verifyAll(result) {
            apiToken.token == 'P82Iosa7_eLb...On8Plw'
        }
    }
}
