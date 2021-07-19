package com.fl.xumm4j;

public class credentials {
    private String apiKey;
    private String secretKey;

    public String getApiKey() {
        return apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public static class Builder{
        private String apiKey;
        private String secretKey;

        public Builder apiKey(String apiKey){
            this.apiKey = apiKey;
            return this;
        }

        public Builder secretKey(String secretKey){
            this.secretKey = secretKey;
            return this;
        }

        public Builder validate() throws Exception {
            if(this.apiKey == null || this.apiKey == ""){
                throw new Exception("Empty API Key");
            }else if(this.secretKey == null || this.secretKey == ""){
                throw new Exception("Empty Secret Key");
            }
            return this;
        }

        public credentials build() throws Exception {
            credentials accessBuilder = new credentials();
            accessBuilder.apiKey = this.apiKey;
            accessBuilder.secretKey = this.secretKey;
            validate();

            return accessBuilder;
        }

    }
    private credentials() {

    }
}
