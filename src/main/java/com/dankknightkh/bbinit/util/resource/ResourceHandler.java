package com.dankknightkh.bbinit.util.resource;

import java.io.InputStream;

public class ResourceHandler {

    private ResourceHandler() {
        throw new IllegalStateException("Utility class");
    }

    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = ResourceHandler.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
