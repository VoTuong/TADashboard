package com.tadashboard.helpers;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.stream.Collectors;

public class StringHelper {
    private static final Faker faker = new Faker();

    public static String getRandomCharacters() {
        return faker.bothify("???###");
    }

    public static String getRandomPageName() {
        return getRandomCharacters() + "_Page";
    }

    public static String getRandomPanelName() {
        return getRandomCharacters() + "_Panel";
    }
    public static String getRandomChartTitle() {
        return getRandomCharacters() + "_ChartTitle";
    }

    public static String getRandomDataProfileName() {
        return getRandomCharacters() + "_DataProfile";
    }

    public static boolean isListStringSorted(List<String> list) {
        List<String> listSorted = list.stream().sorted().collect(Collectors.toList());
        return list.equals(listSorted);
    }

    public static String convertSpaceToNonBreakingSpaceOnText(String text) {
        return text.replace(" ", " ");
    }
}