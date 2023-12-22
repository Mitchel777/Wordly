package org.wordly.command.game;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.wordly.User;

@RunWith(MockitoJUnitRunner.class)
public class TestGame extends TestCommands {
    private Game game = new Game();
    User user = getDefaultUser();
    private String word = getDefaultUser().getWordly().getProvider().getWord();

    @Test
    public void testCheckLetters1() {
        short result = game.checkLetters(word, "почта", user);
        Assert.assertEquals(result, (short)20102);
    }

    @Test
    public void testCheckLetters2() {
        short result = game.checkLetters(word, "квант", user);
        Assert.assertEquals(result, (short)100);
    }

    @Test
    public void testIsWordInFile1() {
        String userWord = "пчела";
        Boolean result = game.isWordInFile(userWord, bot.getFileReader().getWordList());
        Assert.assertEquals(result, true);
    }

    @Test
    public void testIsWordInFile2() {
        String userWord = "клнур";
        Boolean result = game.isWordInFile(userWord, bot.getFileReader().getWordList());
        Assert.assertEquals(result, false);
    }
}
