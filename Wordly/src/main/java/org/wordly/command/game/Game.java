package org.wordly.command.game;

import org.wordly.User;
import org.wordly.command.Command;
import org.wordly.command.ProcessCommand;


import java.util.ArrayList;

public class Game implements Command {
    private String message;
    private final int maxAttempts = 6;

    @Override
    public Command react(String userWord, User user) {

        //проверка
        String word = user.getWord();
        boolean isCorrectWord = word.equals(userWord);

        if (isCorrectWord) {

            message = "Вы угадали слово, поздравляем!!!";
            return new ProcessCommand();
        }
        else {

            message = checkLetters(word, userWord, user);
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

    private String checkLetters(String word, String userWord, User user) {
        int countOfCheckingLetters = Math.min(word.length(), userWord.length());
        String result = "";
        ArrayList<Integer> guessedIndexes1 = new ArrayList<Integer>();
        String[] arrayResult = {"", "", "", "", ""};
        // проверка на совадение соответсвенных букв в словах
        for (int i = 0; i < countOfCheckingLetters; i++) {
            if (word.charAt(i) == userWord.charAt(i)) {
                arrayResult[i] = convertingNumbersToNumerals(i+1) + "буква в Вашем слове находится на том же месте, что и в загаданном слове\n";
                guessedIndexes1.add(i);
                user.storageModel += 2 * Math.pow(10, word.length() - i - 1);
            }
        }
        // проверка на нахождение буквы в слове(но не на той позиции)
        ArrayList <Integer> guessedIndexes2 = new ArrayList<Integer>();
        for (int i : guessedIndexes1) {
            guessedIndexes2.add(i);
        }
        for (int i = 0; i < countOfCheckingLetters; i++) {
            String symbol = userWord.substring(i, i+1);
            if (!guessedIndexes1.contains(i)) {
                if (word.contains(symbol)) {
                    int index = isThisLetterInWord(word, symbol, guessedIndexes2);
                    if (index != -1) {
                        guessedIndexes2.add(index);
                        arrayResult[i] = convertingNumbersToNumerals(i + 1) + "буква в Вашем слове находится в загаданном слове, но не на той позиции\n";
                        user.storageModel += Math.pow(10, word.length() - i - 1);
                    } else {
                        arrayResult[i] = convertingNumbersToNumerals(i + 1) + "буква в Вашем слове не находится в загаданном слове\n";
                    }
                } else {
                    arrayResult[i] = convertingNumbersToNumerals(i + 1) + "буква в Вашем слове не находится в загаданном слове\n";
                }
            }
        }

        for (String str : arrayResult) {
            result = result.concat(str);
        }

        user.storageModel = 0;

        return result;

    }

    private int isThisLetterInWord(String word, String symbol, ArrayList<Integer> guessedIndexes) {
        for (int i = 0; i < word.length(); i++) {
            if (word.substring(i, i+1).equals(symbol) && !guessedIndexes.contains(i)) {
                return i;
            }
        }
        return -1;
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
