package com.dankknightkh.bbinit.util;

import com.dankknightkh.bbinit.util.resource.ResourceHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ResourceHandlerTest {

    @Test
    void testGetResourceFileAsInputStream() {
        InputStream resourceFileAsInputStream = ResourceHandler.getResourceFileAsInputStream("prepscript/test.txt");
        assertNotNull(resourceFileAsInputStream);
    }

    @Test
    void privateConstructorTest() throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        Constructor<ResourceHandler> constructor = ResourceHandler.class.getDeclaredConstructor();
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
