package com.dankknightkh.bbinit.command.catalog;

import com.dankknightkh.bbinit.command.Command;
import com.dankknightkh.bbinit.command.impl.CommandCheckRequirements;
import com.dankknightkh.bbinit.command.impl.CommandNoSuchCommand;
import com.dankknightkh.bbinit.command.impl.CommandSetupStarter;
import com.dankknightkh.bbinit.command.impl.CommandStartBePlatform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

class CommandCatalogImplTest {

    private final CommandNoSuchCommand noSuchCommand = mock(CommandNoSuchCommand.class);
    private final CommandCheckRequirements checkRequirementsCommand = mock(CommandCheckRequirements.class);
    private final CommandSetupStarter setupStarterCommand = mock(CommandSetupStarter.class);
    private final CommandStartBePlatform startBePlatformCommand = mock(CommandStartBePlatform.class);

    private CommandCatalogImpl catalog;

    @BeforeEach
    void setUp() {
        catalog = new CommandCatalogImpl(noSuchCommand, checkRequirementsCommand, setupStarterCommand, startBePlatformCommand);
        catalog.initCommands();
    }

    @Test
    void whenGetCommandByNameExecutedWithProperCommandName_CatalogReturnsCommandInstance() {
        Command noCommand = catalog.getCommandByName("noCommand");
        assertThat(noCommand, is(instanceOf(CommandNoSuchCommand.class)));
    }
}
