package com.dankknightkh.bbinit;

import com.dankknightkh.bbinit.command.CommandCatalog;
import com.dankknightkh.bbinit.resourceutil.ResourceHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class BbinitApplication implements CommandLineRunner {

    private final static String SOME_FILE_REL_PATH = "prepscript/someTxtFile.txt";

    private final CommandCatalog catalog;

    @Autowired
    public BbinitApplication(final CommandCatalog catalog) {
        this.catalog = catalog;
    }

    public static void main(String[] args) {
        log.info("STARTING THE APPLICATION");
        SpringApplication.run(BbinitApplication.class, args);
        log.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) {
        String fileAsString = readFileAsString(SOME_FILE_REL_PATH);
        System.out.println(fileAsString);
        log.info("EXECUTING : command line runner");
        for (int i = 0; i < args.length; ++i) {
            log.info("args[{}]: {}", i, args[i]);
        }
        catalog.getCommandByName("some name");
    }

    public String readFileAsString(String relativePath) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ResourceHandler.getResourceFileAsInputStream(relativePath)));
        return bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
}
