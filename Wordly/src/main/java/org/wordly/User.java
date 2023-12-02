package org.wordly;

import org.wordly.command.Command;
import org.wordly.command.ProcessCommand;

public class User {

    private final long chatID;
    private Command nextCommand = new ProcessCommand();

    public User(long chatID) {
        this.chatID = chatID;
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

}
