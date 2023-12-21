package org.wordly;

import org.junit.Assert;
import org.junit.Test;
import org.wordly.command.game.Game;

import java.lang.reflect.Method;

public class TestGame extends TestCommands {
    public Game game = new Game();

    @Test
    public void testCheckLetters1() {
        String word = "слово";
        String userWord = "волос";
        User user = getDefaultUser();


        try {
            Method method = Game.class.getDeclaredMethod("checkLetters", String.class, String.class, User.class);

            method.setAccessible(true);

            Assert.assertEquals(method.invoke(game, word, userWord, user), (short)11111);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCheckLetters2() {
        String word = "баран";
        String userWord = "квант";
        User user = getDefaultUser();


        try {
            Method method = Game.class.getDeclaredMethod("checkLetters", String.class, String.class, User.class);

            method.setAccessible(true);

            Assert.assertEquals(method.invoke(game, word, userWord, user), (short)110);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCheckLetters3() {
        String word = "олень";
        String userWord = "карат";
        User user = getDefaultUser();


        try {
            Method method = Game.class.getDeclaredMethod("checkLetters", String.class, String.class, User.class);

            method.setAccessible(true);

            Assert.assertEquals(method.invoke(game, word, userWord, user), (short)0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testIsWordInFile1() {
        String userWord = "пчела";
        try {
            Method method = Game.class.getDeclaredMethod("isWordInFile", String.class);

            method.setAccessible(true);

            Assert.assertEquals(method.invoke(game, userWord), true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsWordInFile2() {
        String userWord = "клнур";
        try {
            Method method = Game.class.getDeclaredMethod("isWordInFile", String.class);

            method.setAccessible(true);

            Assert.assertEquals(method.invoke(game, userWord), false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
