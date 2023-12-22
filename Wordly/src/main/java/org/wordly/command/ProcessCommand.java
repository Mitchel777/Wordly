package org.wordly.command;

import org.wordly.User;
import org.wordly.command.game.Game;

public class ProcessCommand implements Command {

    private String message;

    @Override
    public Command react(String message, User user) {

        return switch (message) {
            case "/start" -> {
                this.message = """
                                Привет!
                                Это бот, с которым можно сыграть в игру Wordly
                                Суть игры заключается в следующем:
                                1) Бот загадывает слово из 5 букв, а ваша задача заключается в том, чтобы его разгадать
                                2) Вам дается 6 попыток
                                3) Если вы угадаете букву в этом слове, но она будет не на том месте, то она подсветится курсивом
                                4) Если же вы угадаете букву и на будет на том месте, то она подсветится жирным
                                
                                Если вы хотите сыграть, то напишите команду game
                                Удачи!
                                """;
                yield new ProcessCommand();
            }
            case "/help" -> {
                this.message = """
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
                """;
                yield new ProcessCommand();
            }
            case "/game" -> {
                user.setWord(user.getWordly().getProvider().getWord());
                user.setUserAttemptsZero();
                this.message = "Введите слово из 5 букв";
                yield new Game();
            }
            case "/changeGameMode" -> {
                this.message = user.getWordly().changeGameMode();
                yield new ProcessCommand();
            }
            default -> {
                this.message = """
                Бот не знает такой команды :(
                Бот знает команды:
                1)game - Начать игру
                2)help - Информация о боте
                3)changeGameMode - Смениь режим игры
                """;
                yield new ProcessCommand();
            }
        };
    }

    @Override
    public String getMessage(){
        return message;
    }
}
