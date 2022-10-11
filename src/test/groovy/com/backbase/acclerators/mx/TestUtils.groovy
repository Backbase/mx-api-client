package com.backbase.acclerators.mx

import com.fasterxml.jackson.databind.ObjectMapper

class TestUtils {

    static String toJsonString(Object object) {
        return new ObjectMapper().writeValueAsString(object)
    }
}
