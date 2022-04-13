package com.dankknightkh.bbinit.util.common;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlatformUtilTest {

    @Test
    void testPlatformUtilSetValue() {
        PlatformUtil.setValue("test", "test");
        assertThat(PlatformUtil.getValue("test"), equalTo("test"));
    }

    @Test
    void privateConstructorTest() throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        Constructor<PlatformUtil> constructor = PlatformUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        Throwable currentException = null;
        try {
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            currentException = e;
        }

        assertThat(currentException, isA(InvocationTargetException.class));
        assertThat(currentException != null ? currentException.getCause() : null, isA(IllegalStateException.class));

    }
}
