package com.dankknightkh.bbinit.command.impl;

import com.dankknightkh.bbinit.command.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CommandKillEdgeProcess implements Command {

    @Override
    public void executeCommand() {
        try {
            Runtime.getRuntime().exec("cmd /c start \"\" kill_edge_process.bat");
        } catch (IOException e) {
            log.error("Error while executing start be platform command", e);
        }
    }
}
