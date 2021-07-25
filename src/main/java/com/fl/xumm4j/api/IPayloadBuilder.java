package com.fl.xumm4j.api;

import com.fl.xumm4j.sdk.builder.PayloadBuilder;

public interface IPayloadBuilder {
    String TXJSON_SIGNIN = "{\"TransactionType\": \"SignIn\"}";
    String ERROR_AMBIGUOUS_PAYLOAD = "Error: Ambiguous payload, please specify either txblob or txjson";
    String ERROR_MISSING_PROPERTIES = "Error: txblob or txjson is missing, please add either one of txjson or txblob";
    boolean SUBMIT_TRANSACTION = true;
    boolean MULTISIGN = true;
    double EXPIRE = 60;

    PayloadBuilder.builder identifier(String identifier);
    PayloadBuilder.builder blob(String blob);
    PayloadBuilder.builder instruction(String instruction);
    PayloadBuilder.builder user_token(String user_token);
    PayloadBuilder.builder returnURL_App(String return_url);
    PayloadBuilder.builder returnURL_Web(String return_url);
    PayloadBuilder.builder txjson(String json);
    PayloadBuilder.builder txblob(String blob);
    PayloadBuilder.builder submit(boolean submit);
    PayloadBuilder.builder multisign(boolean multisign);
    PayloadBuilder.builder expire(double expire);
    PayloadBuilder build();
}
