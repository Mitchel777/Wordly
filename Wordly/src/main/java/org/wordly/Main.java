package org.wordly;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.wordly.token.EnvApiKeyProvider;
import org.wordly.word.Pseudorandom;


public class Main {
    public static void main(String[] args) {

        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);

            api.registerBot(new Wordly(new EnvApiKeyProvider(), new Pseudorandom(null)));
        }
        catch (TelegramApiException e) {
            throw new RuntimeException("Cannot register telegram bot : " + e);
        }
    }
}