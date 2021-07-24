package com.fl.xumm4j.api;

import com.fl.xumm4j.Sdk.builder.Payload;

public interface Payloads {

    Payload.builder identifier(String identifier);
    Payload.builder blob(String blob);
    Payload.builder instruction(String instruction);
    Payload.builder user_token(String user_token);
    Payload.builder returnURL_App(String return_url);
    Payload.builder returnURL_Web(String return_url);
    Payload.builder txjson(String json);
    Payload.builder txblob(String blob);
    Payload.builder submit(boolean submit);
    Payload.builder multisign(boolean multisign);
    Payload.builder expire(double expire);
    Payload build();
}
