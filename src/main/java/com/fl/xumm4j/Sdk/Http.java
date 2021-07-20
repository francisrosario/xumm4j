package com.fl.xumm4j.Sdk;

import com.fl.xumm4j.api.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Http implements OkHttp {
    private OkHttpClient okHttpClient;
    private Credentials ic;

    public Http(Credentials iCredentials) {
        this.ic = iCredentials;
    }

    private OkHttpClient okHttpClient(){
        if(this.okHttpClient == null){
            return this.okHttpClient = new OkHttpClient().newBuilder()
                    .build();
        }
        return this.okHttpClient;
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
}
