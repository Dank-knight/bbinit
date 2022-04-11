package com.dankknightkh.bbinit;

import com.dankknightkh.bbinit.command.catalog.CommandCatalog;
import com.dankknightkh.bbinit.util.common.PlatformUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
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
    public static final String PLATFORM_FOLDER_ALIAS = "p";
    public static final String ST = "st";

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
        CommandLine cmd = setupCoomanLoneOptions(args);
        if (cmd.hasOption(PLATFORM_FOLDER_ALIAS)) {
            PlatformUtil.setValue("platform_folder", cmd.getOptionValue(PLATFORM_FOLDER_ALIAS));
        }
        if (isCommandValid(cmd)) {
            catalog.getCommandByName(cmd.getOptionValue(COMMAND_ALIAS)).executeCommand();
        } else {
            catalog.getCommandByName("noCommand").executeCommand();
        }
        if (cmd.hasOption(ST)) {
            catalog.getCommandByName("start").executeCommand();
        }
    }

    private CommandLine setupCoomanLoneOptions(String[] args) throws ParseException {
        Options options = setUpCommandLineOptions();

        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    private Options setUpCommandLineOptions() {
        Options options = new Options();
        options.addOption(Option.builder(PLATFORM_FOLDER_ALIAS)
                .longOpt("platform_folder")
                .hasArg(true)
                .desc("The folder where the platform is located").build());

        options.addOption(Option.builder(COMMAND_ALIAS)
                .longOpt("command_name")
                .hasArg(true)
                .desc("command name").build());

        options.addOption(Option.builder(ST)
                .longOpt("start")
                .hasArg(false)
                .desc("start the application").build());
        return options;
    }

    private boolean isCommandValid(CommandLine cmd) {
        return cmd.hasOption(COMMAND_ALIAS) && cmd.getOptionValue(COMMAND_ALIAS) != null && catalog.getCommandKeys().contains(cmd.getOptionValue(COMMAND_ALIAS));
    }
}
