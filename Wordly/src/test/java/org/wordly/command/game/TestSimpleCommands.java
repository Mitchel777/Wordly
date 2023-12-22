package org.wordly.command.game;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.wordly.command.game.TestCommands;


@RunWith(MockitoJUnitRunner.class)
public class TestSimpleCommands extends TestCommands {

    @Test
    public void testStart() {
        sendDefaultUserMessage("/start");

        Assert.assertEquals(lastAnswer, """
                                Привет!
                                Это бот, с которым можно сыграть в игру Wordly
                                Суть игры заключается в следующем:
                                1) Бот загадывает слово из 5 букв, а ваша задача заключается в том, чтобы его разгадать
                                2) Вам дается 6 попыток
                                3) Если вы угадаете букву в этом слове, но она будет не на том месте, то она подсветится курсивом
                                4) Если же вы угадаете букву и на будет на том месте, то она подсветится жирным
                                
                                Если вы хотите сыграть, то напишите команду game
                                Удачи!
                                """);
    }

    @Test
    public void testHelp() {
        sendDefaultUserMessage("/help");

        Assert.assertEquals(lastAnswer, """
                Это бот, с которым можно сыграть в игру Wordly
                Правила игры:
                1) Бот загадывает слово из 5 букв, а ваша задача заключается в том, чтобы его разгадать
                2) Вам дается 6 попыток
                3) Если вы угадаете букву в этом слове, но она будет не на том месте, то она подсветится оранжевым
                4) Если же вы угадаете букву и на будет на том месте, то она подсветится зеленым
                
                Бот знает команды:
                1)game - Начать игру
                2)help - Информация о боте
                3)changeGameMode - Смениь режим игры
                """);
    }

    @Test
    public void testIncorrectRequest() {
        sendDefaultUserMessage("/sadsad");

        Assert.assertEquals(lastAnswer, """
                Бот не знает такой команды :(
                Бот знает команды:
                1)game - Начать игру
                2)help - Информация о боте
                3)changeGameMode - Смениь режим игры
                """);
    }
}
