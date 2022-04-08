package com.dankknightkh.bbinit.command.impl;

import com.dankknightkh.bbinit.command.Command;
import com.dankknightkh.bbinit.communicator.Speaker;
import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandCheckRequirements implements Command {

    private final Speaker speaker;

    @Autowired
    public CommandCheckRequirements(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public void executeCommand() {
        checkJavaVersion();
        checkMaven();
    }

    private void checkJavaVersion() {
        if (SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_11) && SystemUtils.isJavaVersionAtMost(JavaVersion.JAVA_11)) {
            speaker.speak("java version is just fine");
        } else {
            speaker.speak("Invalid java version. Expected 11. You have: " + SystemUtils.JAVA_VERSION);
        }
    }

    private void checkMaven() {
        if (StringUtils.isBlank(System.getenv("M2_HOME")) && StringUtils.isBlank(System.getenv("MAVEN_HOME"))) {
            speaker.speak("No maven installed, or system variable set");
        } else {
            speaker.speak("Looks like maven installed");
        }
    }
}
