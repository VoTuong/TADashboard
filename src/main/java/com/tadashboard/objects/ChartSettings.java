package com.tadashboard.objects;

import com.tadashboard.common.Constants;
import com.tadashboard.enums.enum_ta.SeriesComboBoxOptions;
import com.tadashboard.helpers.StringHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChartSettings extends Panel {
    String chartTitle;
    boolean isShowTitle;
    String chartType;
    String style;
    String category;
    String categoryCaption;
    SeriesComboBoxOptions series;
    String seriesCaption;
    String legends;
    String dataLabels;

    public static ChartSettings createChartSettings() {
        return new ChartSettings("", false, "", "", "", "", SeriesComboBoxOptions.NAME, "", "", "");
    }

    public static ChartSettings createChartSettingsWithValidSpecialCharactersChartTitle() {
        String chartTitle = StringHelper.getRandomChartTitle() + Constants.VALID_SPECIAL_CHARACTERS;

        ChartSettings chartSettings = createChartSettings();
        chartSettings.setChartTitle(chartTitle);
        return chartSettings;
    }

    public static ChartSettings createChartSettingsWithInValidSpecialCharactersChartTitle() {
        String chartTitle = StringHelper.getRandomChartTitle() + Constants.INVALID_SPECIAL_CHARACTERS;

        ChartSettings chartSettings = createChartSettings();
        chartSettings.setChartTitle(chartTitle);
        return chartSettings;
    }
}
