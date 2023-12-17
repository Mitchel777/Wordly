package org.wordly;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class TestGame extends TestCommands {

    void setWord() {
        User user = getDefaultUser();
        user.setWord("слово");
    }

    @Test
    public void testGame1() {

        sendDefaultUserMessage("/game");
        setWord();
        sendDefaultUserMessage("слово");

        Assert.assertEquals(lastAnswer, "Вы угадали слово, поздравляем!!!");
    }
    @Test
    public void testGame2() {

        sendDefaultUserMessage("/game");
        setWord();
        sendDefaultUserMessage("солбг");

        Assert.assertEquals(lastAnswer, """
                Первая буква в Вашем слове находится на том же месте, что и в загаданном слове
                Вторая буква в Вашем слове находится в загаданном слове, но не на той позиции
                Третья буква в Вашем слове находится в загаданном слове, но не на той позиции
                Четвертая буква в Вашем слове не находится в загаданном слове
                Пятая буква в Вашем слове не находится в загаданном слове
                    
                Введите слово из 5 букв""");
    }

    @Test
    public void testGame3() {

        sendDefaultUserMessage("/game");
        setWord();
        sendDefaultUserMessage("кфыпр");

        Assert.assertEquals(lastAnswer, "Первая буква в Вашем слове не находится в загаданном слове\nВторая буква в Вашем слове не находится в загаданном слове\nТретья буква в Вашем слове не находится в загаданном слове\nЧетвертая буква в Вашем слове не находится в загаданном слове\nПятая буква в Вашем слове не находится в загаданном слове\n\nВведите слово из 5 букв");
    }

    @Test
    public void testHelp() {
        sendDefaultUserMessage("/help");

        Assert.assertEquals(lastAnswer, "Это бот, с которым можно сыграть в игру Wordly\nПравила игры:\n1) Бот загадывает слово из 5 букв, а ваша задача заключается в том, чтобы его разгадать\n2) Вам дается 6 попыток\n3) Если вы угадаете букву в этом слове, но она будет не на том месте, то она подсветится оранжевым\n4) Если же вы угадаете букву и на будет на том месте, то она подсветится зеленым\n\nБот знает команды:\n1)game - Начать игру\n2)help - Информация о боте");
    }

    @Test
    public void testUncorrectRequest() {
        sendDefaultUserMessage("/sadsad");

        Assert.assertEquals(lastAnswer, "Бот не знает такой команды (\nБот знает команды:\n1)game - Начать игру\n2)help - Информация о боте");
    }



}
