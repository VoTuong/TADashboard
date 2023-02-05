package com.tadashboard.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndicatorSettings extends Panel {
    String title;
    boolean isShowTitle;
    String statisticField;
    String value;

    public static IndicatorSettings createIndicatorSettings() {
        return new IndicatorSettings("", false, "", "");
    }
}
