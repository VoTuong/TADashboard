package com.tadashboard.helpers;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public final class Helpers {

    public static String readFile(String file) throws IOException {
        Charset cs = StandardCharsets.UTF_8;
        try (FileInputStream stream = new FileInputStream(file)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        }
    }

    public static String getCurrentDir() {
        return System.getProperty("user.dir") + File.separator;
    }

    public static ArrayList<String> splitString(String str, String valueSplit) {
        ArrayList<String> arrayListString = new ArrayList<>();
        Collections.addAll(arrayListString, str.split(valueSplit, 0));
        return arrayListString;
    }
}
