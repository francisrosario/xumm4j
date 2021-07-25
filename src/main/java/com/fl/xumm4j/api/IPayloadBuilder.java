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

    /**
     * @param identifier
     * @return
     */
    PayloadBuilder.builder identifier(String identifier);

    /**
     * @param blob
     * @return
     */
    PayloadBuilder.builder blob(String blob);

    /**
     * @param instruction
     * @return
     */
    PayloadBuilder.builder instruction(String instruction);

    /**
     * @param user_token
     * @return
     */
    PayloadBuilder.builder user_token(String user_token);

    /**
     * @param return_url
     * @return
     */
    PayloadBuilder.builder returnURL_App(String return_url);

    /**
     * @param return_url
     * @return
     */
    PayloadBuilder.builder returnURL_Web(String return_url);

    /**
     * @param json
     * @return
     */
    PayloadBuilder.builder txjson(String json);

    /**
     * @param blob
     * @return
     */
    PayloadBuilder.builder txblob(String blob);

    /**
     * @param submit
     * @return
     */
    PayloadBuilder.builder submit(boolean submit);

    /**
     * @param multisign
     * @return
     */
    PayloadBuilder.builder multisign(boolean multisign);

    /**
     * @param expire
     * @return
     */
    PayloadBuilder.builder expire(double expire);

    /**
     * @return
     */
    PayloadBuilder build();
}
