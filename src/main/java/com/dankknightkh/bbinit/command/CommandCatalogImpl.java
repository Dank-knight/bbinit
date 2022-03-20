package com.dankknightkh.bbinit.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommandCatalogImpl implements CommandCatalog {

    private final Map<String, Command> commands = new HashMap<>();

    private final NoSuchCommandCommand noSuchCommandCommand;

    @Autowired
    public CommandCatalogImpl(NoSuchCommandCommand noSuchCommandCommand) {
        this.noSuchCommandCommand = noSuchCommandCommand;
    }

    @PostConstruct
    private void initCommands() {
        commands.put("noCommand", noSuchCommandCommand);
    }

    @Override
    public Command getCommandByName(String commandName) {
        Command command = commands.get(commandName);
        if (command == null) {
            command = noSuchCommandCommand;
        }
        return command;
    }
}
