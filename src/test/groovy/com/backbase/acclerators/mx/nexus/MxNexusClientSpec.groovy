package com.backbase.acclerators.mx.nexus

import com.backbase.accelerators.mx.configuration.MxRequestSettings
import com.backbase.accelerators.mx.nexus.client.MxNexusClient
import com.backbase.accelerators.mx.nexus.model.GetAccountDetailsResponse
import com.backbase.accelerators.mx.nexus.model.GetAccountNumbersResponse
import com.backbase.accelerators.mx.nexus.model.GetMemberAccountNumbersResponse
import com.backbase.accelerators.mx.nexus.model.GetSessionMemberResponse
import com.backbase.accelerators.mx.nexus.model.GetSessionMembersResponse
import com.backbase.accelerators.mx.nexus.model.GetSessionTokenResponse
import com.backbase.accelerators.mx.nexus.model.UpdateAccountDetailsResponse
import com.backbase.acclerators.mx.TestData
import com.backbase.acclerators.mx.TestUtils
import spock.lang.Specification

import java.net.http.HttpClient
import java.net.http.HttpResponse

class MxNexusClientSpec extends Specification {

    private MxRequestSettings mxRequestSettings = Mock()
    private HttpClient httpClient = Mock()

    private MxNexusClient mxNexusClient = new MxNexusClient(mxRequestSettings, httpClient)

    void 'getNexusSessionToken returns a session token from MX'() {
        given:
        String mdApiToken = 'fakeToken'

        when:
        GetSessionTokenResponse result = mxNexusClient.getNexusSessionToken(mdApiToken)

        then:
        1 * mxRequestSettings.nexusBaseUrl >> 'https://int-data.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> TestUtils.toJsonString(TestData.getSessionTokenResponse)
                }
        }

        and:
        verifyAll(result.session) {
            token == 'P82Iosa7_eLb...On8Plw'
        }
    }

    void 'getAccountNumbers returns a collection MX account numbers with the given accountId'() {
        given:
        String mxSessionToken = 'fakeSessionToken'
        String mxAccountId = 'fakeAccountId'

        when:
        GetAccountNumbersResponse result = mxNexusClient.getAccountNumbers(mxSessionToken, mxAccountId)

        then:
        1 * mxRequestSettings.nexusBaseUrl >> 'https://int-data.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> TestUtils.toJsonString(TestData.getAccountNumbersResponseBody)
                }
        }

        and:
        verifyAll(result.accountNumbers) {
            it[0].guid == 'ACN-320ec63f-bf52-4563-9119-3893a76cbc81'
            it[0].userGuid == 'USR-d67df65d-574c-4d56-a8ab-087186646b38'
            it[0].memberGuid == 'MBR-21422ef0-a2b8-4a2d-bf98-ce8dd4a4cf61'
            it[0].accountGuid == 'ACT-70242747-6441-46b4-b9c4-e25e06837ee8'
            it[0].accountNumber == '2456635796'
            it[0].routingNumber == '731775673'
            it[0].transitNumber == null
            it[0].institutionNumber == null

        }
    }

    void 'getSessionMember returns a session member from MX with the given memberId'() {
        given:
        String mxSessionToken = 'fakeSessionToken'
        String mxMemberId = 'fakeMemberId'

        when:
        GetSessionMemberResponse result = mxNexusClient.getSessionMember(mxSessionToken, mxMemberId)

        then:
        1 * mxRequestSettings.nexusBaseUrl >> 'https://int-data.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> TestUtils.toJsonString(TestData.getGetSessionMemberResponse)
                }
        }

        and:
        verifyAll(result.member) {
            aggregatedAt == '2021-10-26T19:43:53+00:00'
            connectionStatusMessage == 'Connected to MX Bank (Oauth).'
            externalGuid == null
            guid == 'MBR-21422ef0-a2b8-4a2d-bf98-ce8dd4a4cf61'
            institutionCode == 'mx_bank_oauth'
            institutionGuid == 'INS-68e96dd6-eabd-42d3-9f05-416897f0746c'
            !isBeingAggregated
            !isManual
            isManagedByUser
            isOauth
            metadata == null
            mostRecentJobGuid == 'JOB-b854f03a-8d92-4548-8cfb-1f8e4335c954'
            name == 'MX Bank (Oauth)'
            !needsUpdatedCredentials
            revision == 117
            successfullyAggregatedAt == '2021-10-26T19:43:57+00:00'
            userGuid == 'USR-d67df65d-574c-4d56-a8ab-087186646b38'
            isUserCreated
            connectionStatusId == 6
            connectionStatus == 'CONNECTED'
        }

    }

    void 'getSessionMembers returns a collection session members from MX'() {
        given:
        String mxSessionToken = 'fakeSessionToken'

        when:
        GetSessionMembersResponse result = mxNexusClient.getSessionMembers(mxSessionToken)

        then:
        1 * mxRequestSettings.nexusBaseUrl >> 'https://int-data.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> TestUtils.toJsonString(TestData.getSessionMembersResponse)
                }
        }

        and:
        verifyAll(result.members) {
            it[0].aggregatedAt == '2021-10-26T19:43:53+00:00'
            it[0].connectionStatusMessage == 'Connected to MX Bank (Oauth).'
            it[0].externalGuid == null
            it[0].guid == 'MBR-21422ef0-a2b8-4a2d-bf98-ce8dd4a4cf61'
            it[0].institutionCode == 'mx_bank_oauth'
            it[0].institutionGuid == 'INS-68e96dd6-eabd-42d3-9f05-416897f0746c'
            !it[0].isBeingAggregated
            !it[0].isManual
            it[0].isManagedByUser
            it[0].isOauth
            it[0].metadata == null
            it[0].mostRecentJobGuid == 'JOB-b854f03a-8d92-4548-8cfb-1f8e4335c954'
            it[0].name == 'MX Bank (Oauth)'
            !it[0].needsUpdatedCredentials
            it[0].revision == 117
            it[0].successfullyAggregatedAt == '2021-10-26T19:43:57+00:00'
            it[0].userGuid == 'USR-d67df65d-574c-4d56-a8ab-087186646b38'
            it[0].isUserCreated
            it[0].connectionStatusId == 6
            it[0].connectionStatus == 'CONNECTED'
        }
    }

    void 'getMemberAccountNumbers returns a collection member account numbers from MX with the given memberId'() {
        given:
        String mxSessionToken = 'fakeSessionToken'
        String mxMemberId = 'fakeMemberId'

        when:
        GetMemberAccountNumbersResponse result = mxNexusClient.getMemberAccountNumbers(mxSessionToken, mxMemberId)

        then:
        1 * mxRequestSettings.nexusBaseUrl >> 'https://int-data.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> TestUtils.toJsonString(TestData.getMemberAccountNumbersResponse)
                }
        }

        and:
        verifyAll(result.accountNumbers) {
            it[0].guid ==  'ACN-320ec63f-bf52-4563-9119-3893a76cbc81'
            it[0].userGuid ==  'USR-d67df65d-574c-4d56-a8ab-087186646b38'
            it[0].memberGuid ==  'MBR-21422ef0-a2b8-4a2d-bf98-ce8dd4a4cf61'
            it[0].accountGuid ==  'ACT-70242747-6441-46b4-b9c4-e25e06837ee8'
            it[0].accountNumber ==  '2456635796'
            it[0].routingNumber ==  '731775673'
            it[0].transitNumber ==  null
            it[0].institutionNumber ==  null
        }
    }

    void 'getAccountDetails returns a account details with the given accountId'() {
        given:
        String mxSessionToken = 'fakeSessionToken'
        String mxAccountGuid = 'fakeAccountId'

        when:
        GetAccountDetailsResponse result = mxNexusClient.getAccountDetails(mxSessionToken, mxAccountGuid)

        then:
        1 * mxRequestSettings.nexusBaseUrl >> 'https://int-data.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> TestUtils.toJsonString(TestData.getAccountDetailsResponse)
                }
        }

        and:
        verifyAll(result.account) {
            accountNumber == '2456635796'
            accountType == 2
            accountTypeName == 'SAVINGS'
            createdAt == '2021-09-28T14:56:02+00:00'
            guid == 'ACT-70242747-6441-46b4-b9c4-e25e06837ee8'
            isClosed
            !isHidden
            memberGuid == 'MBR-21422ef0-a2b8-4a2d-bf98-ce8dd4a4cf61'
            name == 'MX Bank Savings'
            nickname == null
        }
    }

    void 'updateAccountNickname updates an account nickname in MX with the given nickname value'() {
        given:
        String mxSessionToken = 'fakeSessionToken'
        String mxAccountGuid = 'fakeAccountId'
        String nickname = 'fakeNickname'

        when:
        UpdateAccountDetailsResponse result = mxNexusClient.updateAccountNickname(mxSessionToken, mxAccountGuid, nickname)

        then:
        1 * mxRequestSettings.nexusBaseUrl >> 'https://int-data.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> TestUtils.toJsonString(TestData.getAccountDetailsResponse)
                }
        }

        and:
        verifyAll(result.account) {
            nickname == 'fakeNickname'
        }
    }

    void 'updateAccountVisibility updates an accounts visibility status to "hidden"'() {
        given:
        String mxSessionToken = 'fakeSessionToken'
        String mxAccountGuid = 'fakeAccountId'
        boolean isHidden = true

        when:
        UpdateAccountDetailsResponse result = mxNexusClient.updateAccountVisibility(mxSessionToken, mxAccountGuid, isHidden)

        then:
        1 * mxRequestSettings.nexusBaseUrl >> 'https://int-data.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> TestUtils.toJsonString(TestData.getAccountDetailsResponse)
                }
        }

        and:
        verifyAll(result.account) {
            isHidden
        }
    }

    void 'deleteMemberAccount removes an account from MX'() {
        given:
        String mxSessionToken = 'fakeSessionToken'
        String mxMemberGuid = 'fakeMemberId'

        when:
         mxNexusClient.deleteMemberAccount(mxSessionToken, mxMemberGuid)

        then:
        1 * mxRequestSettings.nexusBaseUrl >> 'https://int-data.moneydesktop.com'
        1 * mxRequestSettings.mdApiKey >> 'fakeMdApiKey'

        1 * httpClient.send(_, _) >> Mock(HttpResponse) {
            it ->
                {
                    it.statusCode() >> 200
                    it.body() >> ''
                }
        }
    }

}
