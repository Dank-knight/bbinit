package com.dankknightkh.bbinit.command.impl;

import com.dankknightkh.bbinit.command.Command;
import com.dankknightkh.bbinit.communicator.Speaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandNoSuchCommand implements Command {

    private final Speaker speaker;

    @Autowired
    public CommandNoSuchCommand(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public void executeCommand() {
        speaker.speak("No such command found");
    }
}
