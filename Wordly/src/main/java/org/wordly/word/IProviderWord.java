package org.wordly.word;

import org.wordly.WordFileReader;

public interface IProviderWord {
    public String getWord();
    public String whatTheClass();
    public void setFileReader(WordFileReader fileReader);
}
