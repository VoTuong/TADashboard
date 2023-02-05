package com.tadashboard.enums.enum_ta;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NewPanelType {
    CHART("1"), INDICATOR("2"), REPORT("3"), HEAT_MAP("5");

    private final String value;
}
