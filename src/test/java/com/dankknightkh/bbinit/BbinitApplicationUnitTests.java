package com.dankknightkh.bbinit;

import com.dankknightkh.bbinit.command.catalog.CommandCatalog;
import com.dankknightkh.bbinit.command.impl.CommandNoSuchCommand;
import com.dankknightkh.bbinit.command.impl.CommandStartBePlatform;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BbinitApplicationUnitTests {

	private CommandCatalog commandCatalog;
	private CommandNoSuchCommand noSuchCommand;

	private BbInitApplication bbInitApplication;

	@BeforeEach
	void setUp() {
		commandCatalog = mock(CommandCatalog.class);
		noSuchCommand = mock(CommandNoSuchCommand.class);
		bbInitApplication = new BbInitApplication(commandCatalog);
	}

	@Test
	void run_app_with_training_option_and_valid_command() throws ParseException {
		when(commandCatalog.getCommandKeys()).thenReturn(Set.of("noCommand"));
		when(commandCatalog.getCommandByName("noCommand")).thenReturn(noSuchCommand);
		String[] args = {"-p=training", "-c=noCommand"};

		bbInitApplication.run(args);

		verify(commandCatalog, times(1)).getCommandByName("noCommand");
		verify(noSuchCommand, times(1)).executeCommand();
	}

	@Test
	void run_app_with_not_valid_command() throws ParseException {
		when(commandCatalog.getCommandKeys()).thenReturn(Set.of("noCommand"));
		when(commandCatalog.getCommandByName("noCommand")).thenReturn(noSuchCommand);
		String[] args = {"-c=invalidCommandValue"};

		bbInitApplication.run(args);

		verify(commandCatalog, times(1)).getCommandByName("noCommand");
	}

	@Test
	void run_app_with_st_run_option() throws ParseException {
		when(commandCatalog.getCommandKeys()).thenReturn(Set.of("noCommand"));
		when(commandCatalog.getCommandByName("noCommand")).thenReturn(noSuchCommand);
		CommandStartBePlatform commandStartBePlatform = mock(CommandStartBePlatform.class);
		when(commandCatalog.getCommandByName("start")).thenReturn(commandStartBePlatform);
		String[] args = {"-st"};

		bbInitApplication.run(args);

		verify(commandCatalog, times(1)).getCommandByName("noCommand");
		verify(commandStartBePlatform, times(1)).executeCommand();
	}

	@Test
	void run_app_with_null_value_for_command_option() throws ParseException {
		when(commandCatalog.getCommandKeys()).thenReturn(Set.of("noCommand"));
		when(commandCatalog.getCommandByName("noCommand")).thenReturn(noSuchCommand);
		String[] args = {"-c="};

		bbInitApplication.run(args);

		verify(commandCatalog, times(1)).getCommandByName("noCommand");
	}

}
