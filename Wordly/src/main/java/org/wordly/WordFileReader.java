package org.wordly;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordFileReader {
    private List<String> wordList;
    private InputStream filePath = this.getClass().getResourceAsStream("/words.txt");

    public WordFileReader() {
        wordList = new ArrayList<>();
    }

    public void readWordsFromFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordList.add(line);
            }
        }
    }

    public List<String> getWordList() {
        return wordList;
    }
}
