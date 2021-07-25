package com.fl.xumm4j.api;

import com.fl.xumm4j.sdk.builder.PayloadBuilder;

public interface IPayloadBuilder {
    String TXJSON_SIGNIN = "{\"TransactionType\": \"SignIn\"}";
    String ERROR_AMBIGUOUS_PAYLOAD = "Error: Ambiguous payload, please specify either txblob or txjson";
    String ERROR_MISSING_PROPERTIES = "Error: txblob or txjson is missing, please add either one of txjson or txblob";

    String USER_TOKEN = "user_token";
    String TX_BLOB = "txblob";
    String TX_JSON = "txjson";
    String SUBMIT = "submit";
    String MULTISIGN = "multisign";
    String EXPIRE = "expire";
    String APP = "app";
    String WEB = "web";
    String RETURN_URL = "return_url";
    String OPTIONS = "options";
    String IDENTIFIER = "identifier";
    String BLOB = "blob";
    String INSTRUCTION = "instruction";
    String CUSTOM_META = "custom_meta";

    boolean SUBMIT_TRANSACTION_DEFAULT = true;
    boolean MULTISIGN_DEFAULT = true;
    double EXPIRE_DEFAULT = 60;

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
