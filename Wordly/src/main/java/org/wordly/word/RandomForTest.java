package org.wordly.word;

import org.wordly.WordFileReader;

public class RandomForTest implements IProviderWord {
    private final String word;
    private WordFileReader fileReader;
    public RandomForTest() {
        word = "пчела";
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public String whatTheClass() {
        return "RandomForTest";
    }
    @Override
    public void setFileReader(WordFileReader fileReader) {
        this.fileReader = fileReader;
    }
}
