package com.dankknightkh.bbinit.command.catalog;

import com.dankknightkh.bbinit.command.Command;

import java.util.Set;

public interface CommandCatalog {

    Command getCommandByName(String commandName);

    Set<String> getCommandKeys();
}
