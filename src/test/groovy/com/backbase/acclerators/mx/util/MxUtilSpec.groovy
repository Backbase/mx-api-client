package com.backbase.acclerators.mx.util

import com.backbase.accelerators.mx.util.MxUtils
import spock.lang.Specification

class MxUtilSpec extends Specification {

    void "build MX user id"() {
        given:
        String saExternalId = 'sa_sme_518907'
        String environment = 'DEV'
        String identitySubject = 'a11b8b1d-2003-45b4-8ba5-e518bfef2999'

        when:
        String mxUserId = MxUtils.buildMxUserId(environment, saExternalId, identitySubject)

        then:
        mxUserId == 'BB_DEV_-107980962'
    }
}
