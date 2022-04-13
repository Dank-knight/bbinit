package com.dankknightkh.bbinit.util.common;

import java.util.HashMap;
import java.util.Map;

public class PlatformUtil {

    private PlatformUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final Map<String, String> platformFoldersMap = new HashMap<>();

    public static void setValue(String platform, String value) {
        platformFoldersMap.put(platform, value);
    }

    public static String getValue(String platform) {
        return platformFoldersMap.get(platform);
    }
}
