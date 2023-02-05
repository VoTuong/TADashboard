package com.tadashboard.objects;

import com.tadashboard.enums.enum_ta.SeriesComboBoxOptions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeatMapSettings extends Panel {
    String title;
    boolean isShowTitle;
    String category;
    SeriesComboBoxOptions series;
    String seriesValue;
    String legends;

    public static HeatMapSettings createHeatMapSettings() {
        return new HeatMapSettings("", false, "", SeriesComboBoxOptions.NAME, "","");
    }
}
