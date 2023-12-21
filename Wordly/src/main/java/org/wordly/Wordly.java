package org.wordly;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.wordly.command.ProcessCommand;
import org.wordly.token.ApiKeyProvider;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Wordly extends TelegramLongPollingBot {
    private final String token;
    private String word;
    private int indexOfNewWord = 0;
    private final int MAX_SIZE = 1446;
    private final HashMap<Long, User> userHashMap = new HashMap<>();
    private ArrayList<Integer> numberOfTheWords = new ArrayList<>();

    public Wordly(ApiKeyProvider keyProvider) {
        super();
        token = keyProvider.getApiKey();

        randomWord();

        Timer t = new Timer();
        t.scheduleAtFixedRate(
                new TimerTask()
                {
                    public void run()
                    {
                        word = createNewWord();
                        if (indexOfNewWord == MAX_SIZE) {
                            indexOfNewWord = -1;
                        }
                        indexOfNewWord ++;
                    }
                },
                0,
                60000);
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
    private void randomWord() {

        int numberOfTheWord = 954;
        int value = 50;
        numberOfTheWords.add(numberOfTheWord);

        while (numberOfTheWords.size() < 144) {

            numberOfTheWord = ((numberOfTheWords.size() + 1) * 27 + value) % MAX_SIZE;

            while (numberOfTheWords.contains(numberOfTheWord)) {
                value += 29;
                numberOfTheWord = ((numberOfTheWords.size() + 1) * 27 + value) % MAX_SIZE;
            }
            numberOfTheWords.add(numberOfTheWord);
        }
    }
    public String getWord() {
        return word;
    }

    private String createNewWord() {

        String words = "src/main/resources/words.txt";
        String word = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(words));
            int randomNumber = numberOfTheWords.get(indexOfNewWord);
            List<String> lines = new ArrayList<>();
            int counter = 0;
            String line = reader.readLine();
            while (counter != randomNumber) {
                lines.add(line);
                line = reader.readLine();
                counter += 1;
            }
            reader.close();
            word = lines.get(randomNumber - 1);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return word;
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
