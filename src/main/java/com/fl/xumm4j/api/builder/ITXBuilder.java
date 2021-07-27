package com.fl.xumm4j.api.builder;

import com.fl.xumm4j.sdk.builder.TXBuilder;

public interface ITXBuilder {
    String TXJSON_SIGNIN = "{\"TransactionType\": \"SignIn\"}";
    String ERROR_AMBIGUOUS_PAYLOAD = "Error: Ambiguous payload, please specify either txblob or txjson";
    String ERROR_MISSING_PROPERTIES = "Error: txblob or txjson is missing, please add either one of txjson or txblob";

    String KEY_USER_TOKEN = "userToken";
    String KEY_TX_BLOB = "txblob";
    String KEY_TXJSON = "txjson";
    String KEY_SUBMIT = "submit";
    String KEY_MULTISIGN = "multisign";
    String KEY_EXPIRE = "expire";
    String KEY_APP = "app";
    String KEY_WEB = "web";
    String KEY_RETURN_URL = "return_url";
    String KEY_OPTIONS = "options";
    String KEY_IDENTIFIER = "identifier";
    String KEY_BLOB = "blob";
    String KEY_INSTRUCTION = "instruction";
    String KEY_CUSTOM_META = "custom_meta";

    boolean DEFAULT_SUBMIT_TRANSACTION = true;
    boolean DEFAULT_MULTISIGN = true;
    double DEFAULT_EXPIRE = 60;

    /**
     * @param identifier
     * @return
     */
    TXBuilder.builder identifier(String identifier);

    /**
     * @param blob
     * @return
     */
    TXBuilder.builder blob(String blob);

    /**
     * @param instruction
     * @return
     */
    TXBuilder.builder instruction(String instruction);

    /**
     * @param user_token
     * @return
     */
    TXBuilder.builder userToken(String user_token);

    /**
     * @param return_url
     * @return
     */
    TXBuilder.builder returnURL_App(String return_url);

    /**
     * @param return_url
     * @return
     */
    TXBuilder.builder returnURL_Web(String return_url);

    /**
     * @param json
     * @return
     */
    TXBuilder.builder txjson(String json);

    /**
     * @param blob
     * @return
     */
    TXBuilder.builder txblob(String blob);

    /**
     * @param submit
     * @return
     */
    TXBuilder.builder submit(boolean submit);

    /**
     * @param multisign
     * @return
     */
    TXBuilder.builder multisign(boolean multisign);

    /**
     * @param expire
     * @return
     */
    TXBuilder.builder expire(double expire);

    /**
     * @return
     */
    TXBuilder build();
}
