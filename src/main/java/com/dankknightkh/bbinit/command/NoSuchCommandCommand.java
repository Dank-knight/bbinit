package com.dankknightkh.bbinit.command;

import com.dankknightkh.bbinit.communicator.Speaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoSuchCommandCommand implements Command {

    private final Speaker speaker;

    @Autowired
    public NoSuchCommandCommand(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public void executeCommand() {
        speaker.speak("No such command found");
    }
}
