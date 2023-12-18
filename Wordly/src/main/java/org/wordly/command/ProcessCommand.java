package org.wordly.command;

import org.wordly.User;
import org.wordly.command.game.Game;

public class ProcessCommand implements Command {

    private String message;

    @Override
    public Command react(String message, User user) {

        return switch (message) {
            case "/start" -> {
                this.message = "Привет!\nЭто бот, с которым можно сыграть в игру Wordly\nСуть игры заключается в следующем:\n1) Бот загадывает слово из 5 букв, а ваша задача заключается в том, чтобы его разгадать\n2) Вам дается 6 попыток\n3) Если вы угадаете букву в этом слове, но она будет не на том месте, то она подсветится оранжевым\n4) Если же вы угадаете букву и на будет на том месте, то она подсветится зеленым\n\nЕсли вы хотите сыграть, то напишите команду game\nУдачи!";
                yield new ProcessCommand();
            }
            case "/help" -> {
                this.message = "Это бот, с которым можно сыграть в игру Wordly\nПравила игры:\n1) Бот загадывает слово из 5 букв, а ваша задача заключается в том, чтобы его разгадать\n2) Вам дается 6 попыток\n3) Если вы угадаете букву в этом слове, но она будет не на том месте, то она подсветится оранжевым\n4) Если же вы угадаете букву и на будет на том месте, то она подсветится зеленым\n\nБот знает команды:\n1)game - Начать игру\n2)help - Информация о боте";
                yield new ProcessCommand();
            }
            case "/game" -> {
                user.setWord();
                user.setUserAttemptsZero();
                this.message = "Введите слово из 5 букв";
                yield new Game();
            }
            default -> {
                this.message = "Бот не знает такой команды (\nБот знает команды:\n1)game - Начать игру\n2)help - Информация о боте";
                yield new ProcessCommand();
            }
        };
    }

    @Override
    public String getMessage(){
        return message;
    }
}
