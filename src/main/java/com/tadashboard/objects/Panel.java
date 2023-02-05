package com.tadashboard.objects;

import com.tadashboard.enums.enum_ta.NewPanelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Panel {
    NewPanelType newPanelType;
    String dataProfile;
    String displayName;

    ChartSettings chartSettings;
    HeatMapSettings heatMapSettings;
    IndicatorSettings indicatorSettings;

}
