package com.fl.xumm4j.sdk.builder;

import com.fl.xumm4j.api.builder.ICredentialsBuilder;

public class CredentialsBuilder {
    private String apiKey;
    private String secretKey;

    public String getApiKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public static class builder implements ICredentialsBuilder {
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
                if (apiKey == null || apiKey.equals("")) throw new Exception(ICredentialsBuilder.ERROR_APIKEY);
                else if (secretKey == null || secretKey.equals(""))
                    throw new Exception(ICredentialsBuilder.ERROR_SECRET_KEY);
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
