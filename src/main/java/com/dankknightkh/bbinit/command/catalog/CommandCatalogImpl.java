package com.dankknightkh.bbinit.command.catalog;

import com.dankknightkh.bbinit.command.Command;
import com.dankknightkh.bbinit.command.impl.CommandCheckRequirements;
import com.dankknightkh.bbinit.command.impl.CommandNoSuchCommand;
import com.dankknightkh.bbinit.command.impl.CommandSetupStarter;
import com.dankknightkh.bbinit.command.impl.CommandStartBePlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class CommandCatalogImpl implements CommandCatalog {

    private final Map<String, Command> commands = new HashMap<>();

    private final CommandNoSuchCommand noSuchCommandCommand;
    private final CommandCheckRequirements checkRequirementsCommand;
    private final CommandSetupStarter setupStarterCommand;
    private final CommandStartBePlatform startBePlatformCommand;

    @Autowired
    public CommandCatalogImpl(CommandNoSuchCommand noSuchCommandCommand, CommandCheckRequirements checkRequirementsCommand, CommandSetupStarter setupStarterCommand, CommandStartBePlatform startBePlatformCommand) {
        this.noSuchCommandCommand = noSuchCommandCommand;
        this.checkRequirementsCommand = checkRequirementsCommand;
        this.setupStarterCommand = setupStarterCommand;
        this.startBePlatformCommand = startBePlatformCommand;
    }

    @PostConstruct
    protected void initCommands() {
        commands.put("noCommand", noSuchCommandCommand);
        commands.put("req", checkRequirementsCommand);
        commands.put("setup", setupStarterCommand);
        commands.put("start", startBePlatformCommand);
    }

    @Override
    public Command getCommandByName(String commandName) {
        Command command = commands.get(commandName);
        if (command == null) {
            command = noSuchCommandCommand;
        }
        return command;
    }

    @Override
    public Set<String> getCommandKeys() {
        return commands.keySet();
    }
}
