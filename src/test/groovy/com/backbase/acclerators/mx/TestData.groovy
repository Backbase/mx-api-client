package com.backbase.acclerators.mx

import com.backbase.accelerators.mx.microdeposit.model.GetAllMicroDepositsResponse
import com.backbase.accelerators.mx.microdeposit.model.MicroDeposit
import com.backbase.accelerators.mx.microdeposit.model.MicroDepositsAccountResponse
import com.backbase.accelerators.mx.nexus.model.AccountDetail
import com.backbase.accelerators.mx.nexus.model.AccountNumberItem
import com.backbase.accelerators.mx.nexus.model.GetAccountDetailsResponse
import com.backbase.accelerators.mx.nexus.model.GetAccountNumbersResponse
import com.backbase.accelerators.mx.nexus.model.GetMemberAccountNumbersResponse
import com.backbase.accelerators.mx.nexus.model.GetSessionMemberResponse
import com.backbase.accelerators.mx.nexus.model.GetSessionMembersResponse
import com.backbase.accelerators.mx.nexus.model.GetSessionTokenResponse
import com.backbase.accelerators.mx.nexus.model.MemberAccountNumber
import com.backbase.accelerators.mx.nexus.model.NexusSession
import com.backbase.accelerators.mx.nexus.model.SessionMember
import com.backbase.accelerators.mx.nexus.model.SessionRequest
import com.backbase.accelerators.mx.nexus.model.SessionToken
import com.backbase.accelerators.mx.nexus.model.UpdateAccountDetailsResponse
import com.backbase.accelerators.mx.realtime.model.CreateUserRequestBody
import com.backbase.accelerators.mx.realtime.model.CreateUserResponseBody
import com.backbase.accelerators.mx.realtime.model.MxRequestUser
import com.backbase.accelerators.mx.realtime.model.MxUser
import com.backbase.accelerators.mx.sso.model.ApiToken
import com.backbase.accelerators.mx.sso.model.ConnectWidgetUrlRequestBody
import com.backbase.accelerators.mx.sso.model.ConnectWidgetUrlResponseBody
import com.backbase.accelerators.mx.sso.model.GetMxApiTokenResponse
import com.backbase.accelerators.mx.sso.model.MxConnectWidgetUrl
import com.backbase.accelerators.mx.sso.model.MxRequestUrl

class TestData {

    static ConnectWidgetUrlRequestBody connectWidgetUrlRequestBody = new ConnectWidgetUrlRequestBody(
        url: new MxRequestUrl(
            type: 'connect_widget',
            mode: 'verification',
            waitForFullAggregation: false,
            uiMessageVersion: '4'
        )
    )

    static ConnectWidgetUrlRequestBody connectWidgetUrlRequestBodyWithMicroDeposit = new ConnectWidgetUrlRequestBody(
        url: new MxRequestUrl(
            type: 'connect_widget',
            mode: 'verification',
            currentMicroDepositGuid: 'MIC-f1bece53-24cb-40eb-956d-bf3cdd70798e',
            waitForFullAggregation: false,
            uiMessageVersion: '4'
        )
    )

    static ConnectWidgetUrlResponseBody connectWidgetUrlResponseBody = new ConnectWidgetUrlResponseBody(
        url: new MxConnectWidgetUrl(
            url: 'https://int-widgets.moneydesktop.com/md/connect/wtf971...%3D',
            type: 'connect_widget',
            userId: 'BB_DEV_-107980962'
        )
    )

    static CreateUserRequestBody createUserRequestBody = new CreateUserRequestBody(
        user: new MxRequestUser(
            id: 'BB_DEV_-107980962'
        )
    )

    static CreateUserResponseBody createUserResponseBody = new CreateUserResponseBody(
        user: new MxUser(
            id: 'BB_DEV_-107980962',
            guid: 'USR-9ec1871b-b810-4c84-98d5-b93086c867db',
            isDisabled: false
        )
    )

    static SessionRequest nexusSessionRequestBody = new SessionRequest(
        session: new NexusSession(
            skipAggregation: true
        )
    )

    static GetSessionTokenResponse getSessionTokenResponse = new GetSessionTokenResponse(
        session: new SessionToken(
            token: 'P82Iosa7_eLb...On8Plw'
        )
    )


    static GetMxApiTokenResponse getMxApiTokenResponse = new GetMxApiTokenResponse(
        apiToken: new ApiToken(
            token: 'P82Iosa7_eLb...On8Plw'
        )
    )

    static GetAccountNumbersResponse getAccountNumbersResponseBody = new GetAccountNumbersResponse(
        accountNumbers: [
            new AccountNumberItem(
                guid: 'ACN-320ec63f-bf52-4563-9119-3893a76cbc81',
                userGuid: 'USR-d67df65d-574c-4d56-a8ab-087186646b38',
                memberGuid: 'MBR-21422ef0-a2b8-4a2d-bf98-ce8dd4a4cf61',
                accountGuid: 'ACT-70242747-6441-46b4-b9c4-e25e06837ee8',
                accountNumber: '2456635796',
                routingNumber: '731775673',
                transitNumber: null,
                institutionNumber: null
            )
        ]
    )

    static GetSessionMemberResponse getGetSessionMemberResponse = new GetSessionMemberResponse(
        member: new SessionMember(
            aggregatedAt: '2021-10-26T19:43:53+00:00',
            connectionStatusMessage: 'Connected to MX Bank (Oauth).',
            externalGuid: null,
            guid: 'MBR-21422ef0-a2b8-4a2d-bf98-ce8dd4a4cf61',
            institutionCode: 'mx_bank_oauth',
            institutionGuid: 'INS-68e96dd6-eabd-42d3-9f05-416897f0746c',
            isBeingAggregated: false,
            isManual: false,
            isManagedByUser: true,
            isOauth: true,
            metadata: null,
            mostRecentJobGuid: 'JOB-b854f03a-8d92-4548-8cfb-1f8e4335c954',
            name: 'MX Bank (Oauth)',
            needsUpdatedCredentials: false,
            revision: 117,
            successfullyAggregatedAt: '2021-10-26T19:43:57+00:00',
            userGuid: 'USR-d67df65d-574c-4d56-a8ab-087186646b38',
            isUserCreated: true,
            connectionStatusId: 6,
            connectionStatus: 'CONNECTED'
        )
    )

    static GetSessionMembersResponse getSessionMembersResponse = new GetSessionMembersResponse(
        members: [
            new SessionMember(
                aggregatedAt: '2021-10-26T19:43:53+00:00',
                connectionStatusMessage: 'Connected to MX Bank (Oauth).',
                externalGuid: null,
                guid: 'MBR-21422ef0-a2b8-4a2d-bf98-ce8dd4a4cf61',
                institutionCode: 'mx_bank_oauth',
                institutionGuid: 'INS-68e96dd6-eabd-42d3-9f05-416897f0746c',
                isBeingAggregated: false,
                isManual: false,
                isManagedByUser: true,
                isOauth: true,
                metadata: null,
                mostRecentJobGuid: 'JOB-b854f03a-8d92-4548-8cfb-1f8e4335c954',
                name: 'MX Bank (Oauth)',
                needsUpdatedCredentials: false,
                revision: 117,
                successfullyAggregatedAt: '2021-10-26T19:43:57+00:00',
                userGuid: 'USR-d67df65d-574c-4d56-a8ab-087186646b38',
                isUserCreated: true,
                connectionStatusId: 6,
                connectionStatus: 'CONNECTED'
            )
        ]
    )

    static GetMemberAccountNumbersResponse getMemberAccountNumbersResponse = new GetMemberAccountNumbersResponse(
        accountNumbers: [
            new MemberAccountNumber(
                guid: 'ACN-320ec63f-bf52-4563-9119-3893a76cbc81',
                userGuid: 'USR-d67df65d-574c-4d56-a8ab-087186646b38',
                memberGuid: 'MBR-21422ef0-a2b8-4a2d-bf98-ce8dd4a4cf61',
                accountGuid: 'ACT-70242747-6441-46b4-b9c4-e25e06837ee8',
                accountNumber: '2456635796',
                routingNumber: '731775673',
                transitNumber: null,
                institutionNumber: null
            )
        ]
    )

    static GetAccountDetailsResponse getAccountDetailsResponse = new GetAccountDetailsResponse(
        account: new AccountDetail(
            accountNumber: '2456635796',
            accountType: 2,
            accountTypeName: 'SAVINGS',
            createdAt: '2021-09-28T14:56:02+00:00',
            guid: 'ACT-70242747-6441-46b4-b9c4-e25e06837ee8',
            isClosed: true,
            isHidden: false,
            memberGuid: 'MBR-21422ef0-a2b8-4a2d-bf98-ce8dd4a4cf61',
            name: 'MX Bank Savings',
            nickname: null
        )
    )

    static MicroDepositsAccountResponse microDepositAccountResponse = new MicroDepositsAccountResponse(
        microDeposit: new MicroDeposit(
            accountType: 1,
            accountTypeName: 'CHECKING',
            accountNumber: 'vault:v1:cX2x6SEQbZH42VJR+0Vdc4AJLFmGnqJyCwlQ3NZO4Qo=',
            createdAt: '2021-10-08T21:39:07+00:00',
            guid: 'MIC-b6c12062-81d8-415f-b4bf-950db3db3fe7',
            institutionName: null,
            routingNumber: '222345679',
            status: 1,
            statusName: 'REQUESTED',
            updatedAt: '2021-10-08T21:39:07+00:00'
        )
    )

    static GetAllMicroDepositsResponse getAllMicroDepositsResponse = new GetAllMicroDepositsResponse(
        microDeposits: [new MicroDeposit(
            accountType: 1,
            accountTypeName: 'CHECKING',
            accountNumber: 'vault:v1:cX2x6SEQbZH42VJR+0Vdc4AJLFmGnqJyCwlQ3NZO4Qo=',
            createdAt: '2021-10-08T21:39:07+00:00',
            guid: 'MIC-b6c12062-81d8-415f-b4bf-950db3db3fe7',
            institutionName: null,
            routingNumber: '222345679',
            status: 1,
            statusName: 'REQUESTED',
            updatedAt: '2021-10-08T21:39:07+00:00'
        )]
    )

    static UpdateAccountDetailsResponse updateAccountDetailsResponse = new UpdateAccountDetailsResponse(
        account: new AccountDetail(
            nickname: 'fakeNickname',
            isHidden: true
        )
    )


}
