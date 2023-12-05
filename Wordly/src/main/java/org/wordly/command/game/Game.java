package org.wordly.command.game;

import org.wordly.User;
import org.wordly.command.Command;
import org.wordly.command.ProcessCommand;


import java.util.ArrayList;

public class Game implements Command {
    private String message;
    private final int maxAttempts = 6;

    @Override
    public Command react(String word, User user) {

        boolean isCorrectWord = (word.equals(user.getWord()));

        if (isCorrectWord) {

            message = "Вы угадали слово, поздравляем!!!";
            return new ProcessCommand();
        }
        else {

            message = checkigLetters(word, user.getWord());
            user.increaseUserAttempts();
            if (user.getUserAttempts() == maxAttempts) {

                message = message.concat("\nК сожалению, Вам не удалось угадать слово.\n" + "Загаданное слово: " + user.getWord() +"\nМожете попробовать еще раз");
                return new ProcessCommand();
            }
            else {
                message = message.concat("\nВведите слово из 5 букв");
            }
            return new Game();
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    private String checkigLetters(String word, String hiddenWord) {
        int countOfCheckingLetters = Math.min(word.length(), hiddenWord.length());
        String result = "";
        ArrayList<Number> guessedIndexes = new ArrayList<Number>();
        ArrayList<String> arrayResult= new ArrayList<String>();
        // проверка на совадение соответсвенных букв в словах
        for (int i = 0; i < countOfCheckingLetters; i++) {
            if (word.charAt(i) == hiddenWord.charAt(i)) {
                arrayResult.add(i, convertingNumbersToNumerals(i+1) + "буква в Вашем слове находится на том же месте, что и в загаданном слове\n");
                guessedIndexes.add(i);
            }
        }
        // проверка на нахождение буквы в слове(но не на той позиции)
        for (int i = 0; i < countOfCheckingLetters; i++) {
            String symbol = word.substring(i, i+1);
            if (hiddenWord.contains(symbol)) {
                if (!guessedIndexes.contains(i)) {
                    arrayResult.add(i, convertingNumbersToNumerals(i + 1) + "буква в Вашем слове находится в загаданном слове, но не на той позиции\n");
                }
            }
            else {
                arrayResult.add(i, convertingNumbersToNumerals(i+1) + "буква в Вашем слове не находится в загаданном слове\n");
            }
        }

        for (String str : arrayResult) {
            result = result.concat(str);
        }

        return result;
    }

    private String convertingNumbersToNumerals(int number) {
        String result;
        switch (number) {
            case 1 -> {
                result = "Первая ";
            }
            case 2 -> {
                result = "Вторая ";
            }
            case 3 -> {
                result = "Третья ";
            }
            case 4 -> {
                result = "Четвертая ";
            }
            case 5 -> {
                result = "Пятая ";
            }
            default -> {
                result = String.valueOf(number);
            }

        };
        return result;
    }
}
