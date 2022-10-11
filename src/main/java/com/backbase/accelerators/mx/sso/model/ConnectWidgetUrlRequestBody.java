package com.backbase.accelerators.mx.sso.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConnectWidgetUrlRequestBody {

    private MxRequestUrl url;
}
