package com.dankknightkh.bbinit;

import com.dankknightkh.bbinit.command.catalog.CommandCatalog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BbInitApplication implements CommandLineRunner {


    private static final String COMMAND_ALIAS = "c";

    private final CommandCatalog catalog;

    @Autowired
    public BbInitApplication(final CommandCatalog catalog) {
        this.catalog = catalog;
    }

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(BbInitApplication.class, args);
        log.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) throws ParseException {
        Options options = new Options();
        options.addOption(COMMAND_ALIAS, true, "command name");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        if (isCommandValid(cmd)) {
            catalog.getCommandByName(cmd.getOptionValue(COMMAND_ALIAS)).executeCommand();
        } else {
            catalog.getCommandByName("noCommand").executeCommand();
        }

    }

    private boolean isCommandValid(CommandLine cmd) {
        return cmd.hasOption(COMMAND_ALIAS) && cmd.getOptionValue(COMMAND_ALIAS) != null && catalog.getCommandKeys().contains(cmd.getOptionValue(COMMAND_ALIAS));
    }
}
