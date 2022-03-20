package com.dankknightkh.bbinit.resourceutil;

import java.io.InputStream;

public class ResourceHandler {

    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = ResourceHandler.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
