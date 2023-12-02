package org.wordly.command;

import org.wordly.User;

public interface Command {

    Command react(String message, User user);

    String getMessage();
}
