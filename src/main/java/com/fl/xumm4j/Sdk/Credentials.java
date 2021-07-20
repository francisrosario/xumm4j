package com.fl.xumm4j.Sdk;

import com.fl.xumm4j.api.AppCredentials;

public class Credentials {
    private String apiKey;
    private String secretKey;

    public String getApiKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public static class Builder implements AppCredentials {
        private String apiKey;
        private String secretKey;

        @Override
        public Builder apiKey(String apiKey){
            this.apiKey = apiKey;
            return this;
        }

        @Override
        public Builder secretKey(String secretKey){
            this.secretKey = secretKey;
            return this;
        }

        @Override
        public void validate() {
            try {
                if (this.apiKey == null || this.apiKey == "") {
                    throw new Exception(AppCredentials.EMPTY_APIKEY);
                } else if (this.secretKey == null || this.secretKey == "") {
                    throw new Exception(AppCredentials.EMPTY_SECRETKEY);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public Credentials build() {
            Credentials credentials = new Credentials();
            credentials.apiKey = this.apiKey;
            credentials.secretKey = this.secretKey;
            validate();

            return credentials;
        }

    }
    private Credentials() {

    }
}
