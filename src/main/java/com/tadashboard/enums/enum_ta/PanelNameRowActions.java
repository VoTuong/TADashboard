package com.tadashboard.enums.enum_ta;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PanelNameRowActions {
    EDIT("Edit"), DELETE("Delete"), CHECKBOX("chkDelPanel");

    private final String action;
}
