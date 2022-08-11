package com.example.aopday1.logger;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private File file;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private static final String LOGGER_FILE_PATH = "aop_day1/src/main/resources/logs.txt";
    private static final boolean SHOULD_APPEND_FILE = true;
    private BufferedWriter bw;

    public Logger() {
        file = new File(LOGGER_FILE_PATH);
    }

    private void readFile() {
        try {
            fileWriter = new FileWriter(file, SHOULD_APPEND_FILE);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String text) {
        readFile();
        try {
            bufferedWriter.write(text);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
