package com.dankknightkh.bbinit.util.common;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class PlatformUtil {

    private static final Map<String, String> platformFoldersMap = new HashMap<>();

    public static void setValue(String platform, String value) {
        platformFoldersMap.put(platform, value);
    }

    public static String getValue(String platform) {
        return platformFoldersMap.get(platform);
    }
}
