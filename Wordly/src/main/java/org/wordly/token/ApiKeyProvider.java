package org.wordly.token;

public interface ApiKeyProvider {
    String getApiKey() throws RuntimeException;
}
