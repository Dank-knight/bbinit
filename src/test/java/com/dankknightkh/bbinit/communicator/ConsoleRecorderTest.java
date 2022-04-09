package com.dankknightkh.bbinit.communicator;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ConsoleRecorderTest {

    @Test
    void testRecordInput() {
        System.setIn(new ByteArrayInputStream("My string".getBytes()));
        ConsoleRecorder recorder = new ConsoleRecorder();
        String input = recorder.recordInput();

        assertThat(input, is("My string"));
    }
}
