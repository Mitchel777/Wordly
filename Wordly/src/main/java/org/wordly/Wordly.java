package org.wordly;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.wordly.command.ProcessCommand;
import org.wordly.token.ApiKeyProvider;

import java.util.HashMap;

public class Wordly extends TelegramLongPollingBot {
    private final String token;
    private final HashMap<Long, User> userHashMap = new HashMap<>();

    public Wordly(ApiKeyProvider keyProvider) {
        super();

        token = keyProvider.getApiKey();
    }

    @Override
    public String getBotUsername() {
        return "abc123_bot";
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public User getUser(long chatID) {
        if (!userHashMap.containsKey(chatID)) {
            userHashMap.put(chatID, new User(chatID));
        }

        return userHashMap.get(chatID);
    }

    private SendMessage processCommand(User user, Update update) {

        boolean isCommand = update.getMessage().getText().startsWith("/");
        if (isCommand) {
            user.changeCommand(new ProcessCommand());
        }

        String response = user.nextState(update.getMessage().getText());

        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(response);

        return message;
    }


    @Override
    public void onUpdateReceived(Update update) {
        long chatID = update.getMessage().getChatId();
        User user = getUser(chatID);

        SendMessage message = processCommand(user, update);

        try {
            this.execute(message);
        }
        catch(TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
