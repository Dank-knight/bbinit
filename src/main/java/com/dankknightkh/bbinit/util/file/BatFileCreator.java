package com.dankknightkh.bbinit.util.file;

import com.dankknightkh.bbinit.communicator.Speaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class BatFileCreator {

    private final Speaker speaker;

    @Autowired
    public BatFileCreator(Speaker speaker) {
        this.speaker = speaker;
    }

    public File createFile(String fileName) {
        File myObj = null;
        try {
            myObj = new File(fileName);
            if (myObj.createNewFile()) {
                speaker.speak("File created: " + myObj.getName());
            } else {
                speaker.speak("File already exists.");
            }
        } catch (IOException e) {
            speaker.speak("An error occurred during docker running bat file creation.");
        }
        return myObj;
    }

    public void writeCommandToFile(String fileName, String batFileContent) {
        try (FileWriter myWriter = new FileWriter(fileName)) {
            myWriter.write(batFileContent);
        } catch (IOException e) {
            speaker.speak("An error occurred during docker running bat file creation.");
        }
    }
}
