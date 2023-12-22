package org.wordly;

import org.wordly.command.Command;
import org.wordly.command.ProcessCommand;

public class User {

    private final long chatID;
    private int userAttempts;
    private String word;
    private short storageModel = 0;
    private Wordly wordly;
    private Command nextCommand = new ProcessCommand();

    public User(long chatID, Wordly wordly) {
        this.chatID = chatID;
        this.wordly = wordly;
    }

    public Wordly getWordly() {
        return wordly;
    }

    public long getChatID() {
        return chatID;
    }

    public void increaseStorageModel(int number, int position) {
        storageModel += number * Math.pow(10, position);
    }

    public void setStorageModelZero() {
        storageModel = 0;
    }

    public short getStorageModel() {
        return storageModel;
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
    public void setWord(String word) {
        this.word = word;
    }
}
