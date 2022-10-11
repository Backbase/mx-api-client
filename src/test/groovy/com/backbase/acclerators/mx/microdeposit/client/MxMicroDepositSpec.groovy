package com.backbase.acclerators.mx.microdeposit.client

import com.backbase.accelerators.mx.configuration.MxRequestSettings
import com.backbase.accelerators.mx.microdeposit.client.MxMicroDepositClient
import com.backbase.accelerators.mx.microdeposit.model.GetAllMicroDepositsResponse
import com.backbase.accelerators.mx.microdeposit.model.MicroDepositsAccountResponse
import com.backbase.acclerators.mx.TestData
import com.backbase.acclerators.mx.TestUtils
import spock.lang.Specification

import java.net.http.HttpClient
import java.net.http.HttpResponse

class MxMicroDepositSpec extends Specification {

    private MxRequestSettings mxRequestSettings = Mock()
    private HttpClient httpClient = Mock()

    private MxMicroDepositClient mxMicroDepositClient = new MxMicroDepositClient(mxRequestSettings, httpClient)

    void 'getMicroDepositDetails returns a mirco deposit account with the given id'() {
        given:
        String mxSessionToken = 'fakeToken'
        String microDepositId = 'fakeMicroDepositId'

        when:
        MicroDepositsAccountResponse result = mxMicroDepositClient.getMicroDepositDetails(mxSessionToken, microDepositId)

        then:
        1 * mxRequestSettings.nexusBaseUrl >> 'https://int-data.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> TestUtils.toJsonString(TestData.microDepositAccountResponse)
                }
        }

        and:
        verifyAll(result.microDeposit) {
            accountType == 1
            accountTypeName == 'CHECKING'
            accountNumber == 'vault:v1:cX2x6SEQbZH42VJR+0Vdc4AJLFmGnqJyCwlQ3NZO4Qo='
            createdAt == '2021-10-08T21:39:07+00:00'
            guid == 'MIC-b6c12062-81d8-415f-b4bf-950db3db3fe7'
            institutionName == null
            routingNumber == '222345679'
            status == 1
            statusName == 'REQUESTED'
            updatedAt == '2021-10-08T21:39:07+00:00'
        }
    }

    void 'getAllMicroDeposits returns a list of mirco deposit accounts'() {
        given:
        String mxSessionToken = 'fakeToken'

        when:
        GetAllMicroDepositsResponse result = mxMicroDepositClient.getAllMicroDeposits(mxSessionToken)

        then:
        1 * mxRequestSettings.nexusBaseUrl >> 'https://int-data.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> TestUtils.toJsonString(TestData.getAllMicroDepositsResponse)
                }
        }

        and:
        verifyAll(result.microDeposits) {
            it[0].accountType == 1
            it[0].accountTypeName == 'CHECKING'
            it[0].accountNumber == 'vault:v1:cX2x6SEQbZH42VJR+0Vdc4AJLFmGnqJyCwlQ3NZO4Qo='
            it[0].createdAt == '2021-10-08T21:39:07+00:00'
            it[0].guid == 'MIC-b6c12062-81d8-415f-b4bf-950db3db3fe7'
            it[0].institutionName == null
            it[0].routingNumber == '222345679'
            it[0].status == 1
            it[0].statusName == 'REQUESTED'
            it[0].updatedAt == '2021-10-08T21:39:07+00:00'
        }
    }
}
