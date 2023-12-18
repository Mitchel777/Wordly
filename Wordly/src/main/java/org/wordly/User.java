package org.wordly;

import org.wordly.command.Command;
import org.wordly.command.ProcessCommand;
import java.io.*;
import java.util.*;

public class User {

    private final long chatID;
    private int userAttempts;
    private String word;
    public short storageModel = 0;
    public Wordly wordly;
    private Command nextCommand = new ProcessCommand();

    public User(long chatID, Wordly wordly) {
        this.chatID = chatID;
        this.wordly = wordly;
    }

    public long getChatID() {
        return chatID;
    }

    public void changeCommand(Command nextCommand) {
        this.nextCommand = nextCommand;
    }

    public String nextState(String message) {
        Command command = nextCommand.react(message, this);
        String commandMessage = nextCommand.getMessage();
        nextCommand = command;
        return commandMessage;
    }

    public int getUserAttempts() {
        return userAttempts;
    }

    public void setUserAttemptsZero() {
        userAttempts = 0;
    }

    public void increaseUserAttempts() {
        userAttempts++;
    }

    public String getWord() {
        return word;
    }

    public void setWord() {
        this.word = wordly.getWord();
    }

    public void randomWord() {
        String words = "src/main/resources/words.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(words));
            int randomNumber = (int) (Math.random() * 1446) + 1;
            List<String> lines = new ArrayList<>();
            int counter = 0;
            String line = reader.readLine();
            while (counter != randomNumber) {
                lines.add(line);
                line = reader.readLine();
                counter += 1;
            }
            reader.close();
            String word = lines.get(randomNumber - 1);
            setWord();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
