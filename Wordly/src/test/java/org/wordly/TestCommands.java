package org.wordly;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.junit.Before;


public class TestCommands {

    private final long UserChatID = 1;
    protected String lastAnswer;
    protected String word;
    protected Wordly bot;

    public User getDefaultUser() {
        return bot.getUser(UserChatID);
    }

    public void sendMessage(long chatID, String textMessage) {
        Chat chat = new Chat(chatID, "private");

        Message message = new Message();
        message.setChat(chat);
        message.setText(textMessage);

        Update update = new Update();
        update.setMessage(message);

        bot.onUpdateReceived(update);
    }

    void sendDefaultUserMessage(String textMessage) {
        sendMessage(UserChatID, textMessage);
    }


    @Before
    public void setUpBot() throws TelegramApiException {
        bot = Mockito.spy(new Wordly(() -> ""));

        Mockito.doAnswer(invocationOnMock -> {
            lastAnswer = ((SendMessage) invocationOnMock.getArguments()[0]).getText();
            return lastAnswer;
        }).when(bot).execute(ArgumentMatchers.any(SendMessage.class));

        Mockito.doCallRealMethod().when(bot).onUpdateReceived(ArgumentMatchers.any());
    }
}
