package com.fl.xumm4j.Sdk.builder;

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

    public static class builder implements AppCredentials {
        private String apiKey;
        private String secretKey;

        @Override
        public builder apiKey(String apiKey){
            this.apiKey = apiKey;
            return this;
        }

        @Override
        public builder secretKey(String secretKey){
            this.secretKey = secretKey;
            return this;
        }

        private void validate() {
            try {
                if (this.apiKey == null || this.apiKey == "") {
                    throw new Exception(AppCredentials.ERROR_APIKEY);
                } else if (this.secretKey == null || this.secretKey == "") {
                    throw new Exception(AppCredentials.ERROR_SECRETKEY);
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
