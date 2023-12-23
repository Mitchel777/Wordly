package org.wordly.command.game;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.wordly.User;
import org.wordly.WordFileReader;
import org.wordly.Wordly;
import org.wordly.token.EnvApiKeyProvider;
import org.wordly.word.RandomForTest;

@RunWith(MockitoJUnitRunner.class)
public class TestGame extends TestCommands {
    @Override
    public void setUpBot() throws TelegramApiException {
        keyProvider = new EnvApiKeyProvider();
        setToken();
        bot = new Wordly(new EnvApiKeyProvider(), new RandomForTest(new WordFileReader()));
        word = bot.getProvider().getWord();
    }
    @Test
    public void testCheckLetters1() {
        Game game = new Game();
        User user = getDefaultUser();
        short result = game.checkLetters(word, "почта", user);
        Assert.assertEquals(result, (short)20102);
    }

    @Test
    public void testCheckLetters2() {
        Game game = new Game();
        User user = getDefaultUser();
        short result = game.checkLetters(word, "квант", user);
        Assert.assertEquals(result, (short)100);
    }

    @Test
    public void testIsWordInFile1() {
        Game game = new Game();
        String userWord = "пчела";
        Boolean result = game.isWordInFile(userWord, bot.getFileReader().getWordList());
        Assert.assertEquals(result, true);
    }

    @Test
    public void testIsWordInFile2() {
        Game game = new Game();
        String userWord = "клнур";
        Boolean result = game.isWordInFile(userWord, bot.getFileReader().getWordList());
        Assert.assertEquals(result, false);
    }
}
