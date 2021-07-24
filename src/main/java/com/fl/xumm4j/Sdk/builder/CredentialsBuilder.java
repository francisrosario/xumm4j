package com.fl.xumm4j.Sdk.builder;

import com.fl.xumm4j.api.ICredentials;

public class CredentialsBuilder {
    private String apiKey;
    private String secretKey;

    public String getApiKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public static class builder implements ICredentials {
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
                if (apiKey == null || apiKey == "") {
                    throw new Exception(ICredentials.ERROR_APIKEY);
                } else if (secretKey == null || secretKey == "") {
                    throw new Exception(ICredentials.ERROR_SECRETKEY);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public CredentialsBuilder build() {
            CredentialsBuilder credentials = new CredentialsBuilder();
            credentials.apiKey = apiKey;
            credentials.secretKey = secretKey;
            validate();

            return credentials;
        }

    }
    private CredentialsBuilder() {

    }
}
