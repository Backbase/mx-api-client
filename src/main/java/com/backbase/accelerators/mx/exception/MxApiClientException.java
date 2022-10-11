package com.backbase.accelerators.mx.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MxApiClientException extends RuntimeException {

    private int statusCode;

    public MxApiClientException(String message) {
        super(message);
    }
}
