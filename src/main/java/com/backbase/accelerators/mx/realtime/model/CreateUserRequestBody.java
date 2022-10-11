package com.backbase.accelerators.mx.realtime.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUserRequestBody {

    private MxRequestUser user;
}
