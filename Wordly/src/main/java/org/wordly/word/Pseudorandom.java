package org.wordly.word;


import org.wordly.WordFileReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Pseudorandom implements IProviderWord {

    private String word;
    private WordFileReader fileReader;
    private final int MAX_SIZE = 144;
    private int indexOfNewWord = 0;
    private ArrayList<Integer> numberOfTheWords = new ArrayList<>();

    public Pseudorandom() {
        pseudorandomIndex();

        Timer t = new Timer();
        t.scheduleAtFixedRate(
                new TimerTask()
                {
                    public void run()
                    {
                        setWord();
                        if (indexOfNewWord == MAX_SIZE) {
                            indexOfNewWord = -1;
                        }
                        indexOfNewWord ++;
                    }
                },
                0,
                60000);
    }

    private void pseudorandomIndex() {

        int numberOfTheWord = 954;
        int value = 50;
        int step = 29;

        numberOfTheWords.add(numberOfTheWord);

        while (numberOfTheWords.size() < MAX_SIZE) {

            numberOfTheWord = ((numberOfTheWords.size() + 1) * 27 + value) % MAX_SIZE;

            while (numberOfTheWords.contains(numberOfTheWord)) {
                value += step;
                numberOfTheWord = ((numberOfTheWords.size() + 1) * 27 + value) % MAX_SIZE;
            }
            numberOfTheWords.add(numberOfTheWord);
        }
    }

    public void setWord() {
        int randomNumber = numberOfTheWords.get(indexOfNewWord);
        word = fileReader.getWordList().get(randomNumber - 1);
    }

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public String whatTheClass() {
        return "Pseudorandom";
    }

    @Override
    public void setFileReader(WordFileReader fileReader) {
        this.fileReader = fileReader;
    }
}
