package org.wordly;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.wordly.command.ProcessCommand;
import org.wordly.token.ApiKeyProvider;
import org.wordly.word.IProviderWord;
import org.wordly.word.Pseudorandom;
import org.wordly.word.Random;


import java.io.IOException;
import java.util.*;

public class Wordly extends TelegramLongPollingBot {
    private final String token;
    private IProviderWord provider;

    private WordFileReader fileReader;
    private final HashMap<Long, User> userHashMap = new HashMap<>();

    public Wordly(ApiKeyProvider keyProvider, IProviderWord provider) {
        super();
        fileReader = new WordFileReader();
        try {
            fileReader.readWordsFromFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.provider = provider;
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
            userHashMap.put(chatID, new User(chatID, this));
        }

        return userHashMap.get(chatID);
    }

    public IProviderWord getProvider() {
        return provider;
    }

    public WordFileReader getFileReader() {
        return fileReader;
    }
    public String changeGameMode() {
        if (provider.whatTheClass().equals("Pseudorandom")) {
            provider = new Random();
            return "Вы перешли из рандомного режима игры в многопользовательский";
        }
        else {
            provider = new Pseudorandom();
            return "Вы перешли из многопользовательского режима игры в рандомный";
        }
    }

    private SendMessage processCommand(User user, Update update) {


        boolean isCommand = update.getMessage().getText().startsWith("/");
        if (isCommand) {
            user.changeCommand(new ProcessCommand());
        }

        String response = user.nextState(update.getMessage().getText());

        SendMessage message = new SendMessage();
        message.enableHtml(true);
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
