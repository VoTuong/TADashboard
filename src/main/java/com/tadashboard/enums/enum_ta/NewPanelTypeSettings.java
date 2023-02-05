package com.tadashboard.enums.enum_ta;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NewPanelTypeSettings {
    CHART_SETTINGS("Chart Settings"), INDICATOR_SETTINGS("Indicator Settings"), HEAT_MAP_SETTINGS("Heat Map Settings");

    private final String value;
}
