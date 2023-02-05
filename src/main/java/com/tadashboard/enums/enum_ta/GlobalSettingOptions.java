package com.tadashboard.enums.enum_ta;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum GlobalSettingOptions {
    ADD_PAGE("Add Page"), DELETE("Delete");
    @Getter
    private final String value;

}
