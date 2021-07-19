package com.fl.xumm4j.api;

import com.fl.xumm4j.impl.ICredentials;

public interface credentials{
    String EMPTY_APIKEY = "Empty API Key";
    String EMPTY_SECRETKEY = "Empty API Key";

    ICredentials.Builder apiKey(String apiKey);
    ICredentials.Builder secretKey(String secretKey);
    void validate();
    ICredentials build();
}
