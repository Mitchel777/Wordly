package org.wordly.token;

public class EnvApiKeyProvider implements ApiKeyProvider {

    @Override
    public String getApiKey() throws RuntimeException {
        String token = System.getenv("TOKEN");

        if (token == null) {
            throw new RuntimeException("Can't get token from environment");
        }
        return token;
    }
}