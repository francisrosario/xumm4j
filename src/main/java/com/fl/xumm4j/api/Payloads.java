package com.fl.xumm4j.api;

import com.fl.xumm4j.Sdk.builder.Payload;

public interface Payloads {
    String ERROR_AMBIGOUS_PAYLOAD = "Error: Ambiguous payload, please specify either txblob or txjson";
    String ERROR_MISSING_PROPERTIES = "Error: txblob or txjson is missing, please add either one of txjson or txblob";
    boolean SUBMIT_TRANSACTION = true;
    boolean MULTISIGN = true;
    double EXPIRE = 60l;

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
