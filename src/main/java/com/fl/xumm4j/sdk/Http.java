package com.fl.xumm4j.sdk;

import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import com.fl.xumm4j.api.IOkHttp;
import okhttp3.*;

import java.io.IOException;

public class Http implements IOkHttp {
    private OkHttpClient okHttpClient;
    private final CredentialsBuilder ic;

    public Http(CredentialsBuilder iCredentials) {
        this.ic = iCredentials;
    }

    private OkHttpClient okHttpClient(){
        if(okHttpClient == null){
            return okHttpClient = new OkHttpClient().newBuilder()
                    .build();
        }
        return okHttpClient;
    }

    @Override
    public Response doGet(String url) {
        Response response = null;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-API-Key", ic.getApiKey())
                .addHeader("X-API-Secret", ic.getSecretKey())
                .build();
        try {
            response = okHttpClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response doPost(String payload) {
        Response response = null;
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, payload);

        Request request = new Request.Builder()
                .url("https://xumm.app/api/v1/platform/payload")
                .addHeader("X-API-Key", ic.getApiKey())
                .addHeader("X-API-Secret", ic.getSecretKey())
                .method("POST", body)
                .build();
        try {
            response = okHttpClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
