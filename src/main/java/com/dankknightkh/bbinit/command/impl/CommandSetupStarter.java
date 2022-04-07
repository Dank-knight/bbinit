package com.dankknightkh.bbinit.command.impl;

import com.dankknightkh.bbinit.command.Command;
import com.dankknightkh.bbinit.communicator.Recorder;
import com.dankknightkh.bbinit.communicator.Speaker;
import com.dankknightkh.bbinit.util.file.BatFileCreator;
import com.dankknightkh.bbinit.util.resource.ResourceHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CommandSetupStarter implements Command {

    private static final String PREPARED_DOCKER_COMPOSE_UP_SCRIPT = "prepscript/docker_compose_up.txt";
    public static final String PLATFORM_FOLDER_NAME_PLACEHOLDER = "{platformFolderName}";
    private static final String PREPARED_BLADE_RUN_SCRIPT = "prepscript/blade_run.txt";
    private static final String PREPARED_EDGE_RUN_SCRIPT = "prepscript/edge_run.txt";
    private static final String PREPARED_STARTER_RUN_SCRIPT = "prepscript/starter.txt";

    private final Speaker speaker;
    private final Recorder recorder;
    private final BatFileCreator batFileCreator;

    @Autowired
    public CommandSetupStarter(Speaker speaker, Recorder recorder, BatFileCreator batFileCreator) {
        this.speaker = speaker;
        this.recorder = recorder;
        this.batFileCreator = batFileCreator;
    }

    @Override
    public void executeCommand() {
        sayCurrentDirectory();
        sayWhereTheFileShouldBe();
        String platformFolder = askForNameOfBackBasePlatformFolder();
        createDockerComposeUpBatFile(platformFolder);
        createPlatformBladeRunFile(platformFolder);
        createPlatformEdgeRunFile(platformFolder);
        createOrchestratorBat();
    }

    private void sayCurrentDirectory() {
        speaker.speak("current directory is: " + System.getProperty("user.dir"));
    }

    private void sayWhereTheFileShouldBe() {
        speaker.speak("The jar should be executed from folder at the same lvl " +
                "where downloaded folder with bb platform lays!");
    }

    private String askForNameOfBackBasePlatformFolder() {
        while (true) {
            speaker.speak("Type name of backbase platform folder. The folder where 'cxs', 'dbs', 'platform'," +
                    " 'statics' folders are.");
            String platformFolderName = recorder.recordInput();
            speaker.speak("Did you spell it correct? " + platformFolderName);
            speaker.speak("Type y/n");
            String isFolderNameSpelledCorrectly = recorder.recordInput().toLowerCase(Locale.ROOT);
            if (isFolderNameSpelledCorrectly.equals("y")) {
                return platformFolderName;
            }
        }
    }

    private void createDockerComposeUpBatFile(String platformFolderName) {
        try {
            batFileCreator.createFile("docker_compose_up.bat");
            String content = readFileAsString(PREPARED_DOCKER_COMPOSE_UP_SCRIPT);
            content = content.replace(PLATFORM_FOLDER_NAME_PLACEHOLDER, platformFolderName);
            batFileCreator.writeCommandToFile("docker_compose_up.bat", content);
        } catch (IOException e) {
            log.error("An error occurred during docker_compose_up script creation.");
        }
    }

    private void createPlatformBladeRunFile(String platformFolderName) {
        try {
            batFileCreator.createFile("platform_blade_run.bat");
            String content = readFileAsString(PREPARED_BLADE_RUN_SCRIPT);
            content = content.replace(PLATFORM_FOLDER_NAME_PLACEHOLDER, platformFolderName);
            batFileCreator.writeCommandToFile("platform_blade_run.bat", content);
        } catch (IOException e) {
            log.error("An error occurred during platform_blade_run script creation.");
        }
    }

    private void createPlatformEdgeRunFile(String platformFolderName) {
        try {
            batFileCreator.createFile("edge_run.bat");
            String content = readFileAsString(PREPARED_EDGE_RUN_SCRIPT);
            content = content.replace(PLATFORM_FOLDER_NAME_PLACEHOLDER, platformFolderName);
            batFileCreator.writeCommandToFile("edge_run.bat", content);
        } catch (IOException e) {
            log.error("An error occurred during blade_run script creation.");
        }
    }

    private void createOrchestratorBat() {
        try {
            batFileCreator.createFile("starter.bat");
            String content = readFileAsString(PREPARED_STARTER_RUN_SCRIPT);
            batFileCreator.writeCommandToFile("starter.bat", content);
        } catch (IOException e) {
            log.error("An error occurred during blade_run script creation.");
        }
    }

    public String readFileAsString(String relativePath) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ResourceHandler.getResourceFileAsInputStream(relativePath)))) {
            return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
