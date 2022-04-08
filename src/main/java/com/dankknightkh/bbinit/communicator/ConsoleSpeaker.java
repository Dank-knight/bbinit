package com.dankknightkh.bbinit.communicator;

import org.springframework.stereotype.Component;

@Component
public class ConsoleSpeaker implements Speaker {

    @Override
    public void speak(String msg) {
        System.out.println(msg);
    }
}
