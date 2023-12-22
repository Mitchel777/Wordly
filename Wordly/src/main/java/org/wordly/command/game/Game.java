package org.wordly.command.game;

import org.wordly.User;
import org.wordly.command.Command;
import org.wordly.command.ProcessCommand;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game implements Command {
    private String message;
    private final int maxAttempts = 6;

    @Override
    public Command react(String userWord, User user) {

        //проверка
        String word = user.getWord();

        if (userWord.length() != 5) {
            if (userWord.length() > 5) {
                message = "Длина слова превышает 5 букв. Попробуйте снова";
            }
            else {
                message = "Длина слова менее 5 букв. Попробуйте снова";
            }
            return new Game();
        }

        if (!isWordInFile(userWord, user.getWordly().getFileReader().getWordList())) {
            message = "Я не знаю такого слова. Введите другое";
            return new Game();
        }

        if (word.equals(userWord)) {
            message = "Вы угадали слово, поздравляем!!!";
            return new ProcessCommand();
        }
        else {


            message = printResult(checkLetters(word, userWord, user), userWord);
            user.setStorageModelZero();
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

    private String printResult(short storageModel, String userWord) {
        String result = new String();
        String number = String.valueOf(storageModel);
        int len = userWord.length() - number.length();
        for (int i = 0; i < len; i++) {
            result += userWord.substring(i, i+1);
        }

        for (int i = len; i < userWord.length(); i++) {
            if (number.charAt(i - len) == '2') {
                result += "<b>" + userWord.charAt(i) + "</b>";
            }
            else if (number.charAt(i - len) == '1') {
                result += "<i>" + userWord.charAt(i) + "</i>";
            }
            else {
                result += userWord.substring(i, i+1);
            }
        }
        return result;
    }

    short checkLetters(String word, String userWord, User user) {
        ArrayList<Integer> guessedIndexes1 = new ArrayList<Integer>();
        // проверка на совадение соответсвенных букв в словах
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == userWord.charAt(i)) {
                guessedIndexes1.add(i);
                user.increaseStorageModel(2, word.length() - i - 1);
            }
        }
        // проверка на нахождение буквы в слове(но не на той позиции)
        ArrayList <Integer> guessedIndexes2 = new ArrayList<Integer>();
        for (int i : guessedIndexes1) {
            guessedIndexes2.add(i);
        }
        for (int i = 0; i < word.length(); i++) {
            String symbol = userWord.substring(i, i+1);
            if (!guessedIndexes1.contains(i)) {
                if (word.contains(symbol)) {
                    int index = isThisLetterInWord(word, symbol, guessedIndexes2);
                    if (index != -1) {
                        guessedIndexes2.add(index);
                        user.increaseStorageModel(1, word.length() - i - 1);
                    }
                }
            }
        }
        return user.getStorageModel();
    }

    private int isThisLetterInWord(String word, String symbol, ArrayList<Integer> guessedIndexes) {
        for (int i = 0; i < word.length(); i++) {
            if (word.substring(i, i+1).equals(symbol) && !guessedIndexes.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    boolean isWordInFile(String word, List<String> wordList) {
        HashSet<String> words = new HashSet<>(wordList);
        return words.contains(word);
    }
}
