package com.fl.xumm4j.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xumm4j.dao.StorageDAO;
import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import com.fl.xumm4j.api.IXummClient;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

public class XummClient implements IXummClient {
    private final HttpClient http;
    private final Deserialize deserialize;
    private final ObjectMapper mapper;
    private String response;

    public XummClient(CredentialsBuilder credentials) {
        http = new HttpClient(credentials);
        mapper = new ObjectMapper();
        deserialize = new Deserialize();
    }

    private StorageDAO getStorageDao() {
        StorageDAO storagedao = deserialize.Storage(response);
        return storagedao;
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
            final JSONObject data = new JSONObject();
            data.put("user_token", UserToken_ClassicAddress);
            try {
                response = Objects.requireNonNull(http.doPost(ENDPOINT_KYC_STATUS, data.toString()).body()).string();
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
    public String postPayload(String payload) {
        try {
            response = Objects.requireNonNull(http.doPost(ENDPOINT_PAYLOAD, payload).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String getPayload(String payloadUUID) {
        try {
            response = Objects.requireNonNull(http.doGet(ENDPOINT_GET_PAYLOAD + payloadUUID).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String getCustomIdentifier(String payloadUUID) {
        try {
            response = Objects.requireNonNull(http.doGet(ENDPOINT_GET_PAYLOAD_CUSTOM_IDENTIFIER + payloadUUID).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String deletePayload(String payloadUUID) {
        try {
            response = Objects.requireNonNull(http.doDelete(ENDPOINT_DELETE_PAYLOAD + payloadUUID).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public boolean setStorage(String json){
        try {
            response = Objects.requireNonNull(http.doPost(ENDPOINT_STORE_APP_STORAGE, json).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageDAO storagedao = getStorageDao();
        return storagedao.isStored();
    }

    public String getStorage(){
        try {
            response = Objects.requireNonNull(http.doGet(ENDPOINT_GET_APP_STORAGE).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageDAO storagedao = getStorageDao();
        return storagedao.getData();
    }

    public boolean deleteStorage(){
        try {
            response = Objects.requireNonNull(http.doDelete(ENDPOINT_DELETE_APP_STORAGE).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StorageDAO storagedao = getStorageDao();
        return storagedao.isStored();
    }

}
