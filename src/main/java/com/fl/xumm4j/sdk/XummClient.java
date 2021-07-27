package com.fl.xumm4j.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import com.fl.xumm4j.api.IXummClient;
import java.io.IOException;
import java.util.Objects;

public class XummClient implements IXummClient {
    private final HttpClient http;
    private final ObjectMapper mapper;
    private String response;

    public XummClient(CredentialsBuilder credentials) {
        http = new HttpClient(credentials);
        mapper = new ObjectMapper();
    }

    private String getToPrettyString(String response) throws JsonProcessingException {
        return mapper.readTree(response).toPrettyString();
    }

    @Override
    public String doPing() {
        try {
            response = Objects.requireNonNull(http.doGet(ENDPOINT_PING).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String getCuratedAssets() {
        try {
            response = Objects.requireNonNull(http.doGet(ENDPOINT_CURATED_ASSETS).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String getRates(String currencyCode) {
        try {
            response = Objects.requireNonNull(http.doGet(ENDPOINT_RATES + currencyCode).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String getKycStatus(String UserToken_ClassicAddress) {
        if(UserToken_ClassicAddress.length() <= 35 ){
            try {
                response = Objects.requireNonNull(http.doGet(ENDPOINT_KYC_STATUS_PUBLIC + UserToken_ClassicAddress).body()).string();
                response = getToPrettyString(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response = Objects.requireNonNull(http.doPost(UserToken_ClassicAddress, ENDPOINT_KYC_STATUS).body()).string();
                response = getToPrettyString(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }


    @Override
    public String getTransaction(String txHash) {
        try {
            response = Objects.requireNonNull(http.doGet(ENDPOINT_TXID + txHash).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String create(String txJson) {
        try {
            response = Objects.requireNonNull(http.doPost(txJson, ENDPOINT_PAYLOAD).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
