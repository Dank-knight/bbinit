package com.dankknightkh.bbinit.communicator;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRecorder implements Recorder {

    private final Scanner scanner;

    public ConsoleRecorder() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String recordInput() {
        return scanner.nextLine();
    }
}
