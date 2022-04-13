package com.dankknightkh.bbinit.command.catalog;

import com.dankknightkh.bbinit.command.Command;
import com.dankknightkh.bbinit.command.impl.CommandCheckRequirements;
import com.dankknightkh.bbinit.command.impl.CommandKillEdgeProcess;
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

    private static final int NUMBER_OF_COMMANDS_KEYS_AT_THE_MOMENT_OF_TEST_WRITING = 5;
    private final CommandNoSuchCommand noSuchCommand = mock(CommandNoSuchCommand.class);
    private final CommandCheckRequirements checkRequirementsCommand = mock(CommandCheckRequirements.class);
    private final CommandSetupStarter setupStarterCommand = mock(CommandSetupStarter.class);
    private final CommandStartBePlatform startBePlatformCommand = mock(CommandStartBePlatform.class);
    private final CommandKillEdgeProcess killEdgeProcessCommand = mock(CommandKillEdgeProcess.class);

    private CommandCatalogImpl catalog;

    @BeforeEach
    void setUp() {
        catalog = new CommandCatalogImpl(noSuchCommand, checkRequirementsCommand, setupStarterCommand,
                startBePlatformCommand, killEdgeProcessCommand);
        catalog.initCommands();
    }

    @Test
    void whenGetCommandByNameExecutedWithProperCommandName_CatalogReturnsCommandInstance() {
        Command noCommand = catalog.getCommandByName("setup");
        assertThat(noCommand, is(instanceOf(CommandSetupStarter.class)));
    }

    @Test
    void whenGetCommandByNameExecutedWithNonExistentCommandName_CatalogReturnsNoSuchCommandInstance() {
        Command noCommand = catalog.getCommandByName("invalidCommandName");
        assertThat(noCommand, is(instanceOf(CommandNoSuchCommand.class)));
    }

    @Test
    void whenGetCommandByNameExecutedWithNullCommandName_CatalogReturnsNoSuchCommandInstance() {
        Command noCommand = catalog.getCommandByName(null);
        assertThat(noCommand, is(instanceOf(CommandNoSuchCommand.class)));
    }

    @Test
    void whenGetCommandsExecuted_CatalogReturnsAllCommands() {
        assertThat(catalog.getCommandKeys().size(), is(NUMBER_OF_COMMANDS_KEYS_AT_THE_MOMENT_OF_TEST_WRITING));
    }
}
