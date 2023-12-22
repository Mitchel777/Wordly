package org.wordly.word;


import org.wordly.WordFileReader;



public class Random implements IProviderWord  {

    private String word;
    private WordFileReader fileReader;

    public void setRandomWord() {
        int randomNumber = (int) (Math.random() * 1446) + 1;
        word = fileReader.getWordList().get(randomNumber - 1);
    }

    @Override
    public String getWord() {
        setRandomWord();
        return word;
    }

    @Override
    public void setFileReader(WordFileReader fileReader) {
        this.fileReader = fileReader;
    }
    @Override
    public String whatTheClass() {
        return "Random";
    }
}
