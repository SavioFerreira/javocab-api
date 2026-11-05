package com.sfc.dictionary;

import java.io.IOException;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped 
public class DictionaryClient {

    @ConfigProperty(name = "dictionary.path")
    String dicionaryPath;

    @ConfigProperty(name = "frequency.path")
    String frequencyPath;

    @ConfigProperty(name = "words.path")
    String wordsPath;


    public boolean wordExists(String word) {
        final String grepCommand = "grep";
        final String searchParameter = "-qFx";
        // "--" ends option parsing: everything after it is a pattern/file, never a flag (e.g. "-V")
        final String endOfOptions = "--";
        ProcessBuilder pb = new ProcessBuilder(grepCommand, searchParameter, endOfOptions, word, wordsPath);
        try {
            Process process = pb.start();
            int exitCode = process.waitFor();

            switch (exitCode) {
                case 0:
                    return true;
                case 1:
                    return false;
                default:
                    throw new DictionaryUnavailableException(
                            "grep exited with code " + exitCode + " while reading words file: " + wordsPath);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new DictionaryUnavailableException("Interrupted while searching words file: " + wordsPath, e);
        } catch (IOException e) {
            throw new DictionaryUnavailableException("Could not start grep to read words file: " + wordsPath, e);
        }
    }
}
