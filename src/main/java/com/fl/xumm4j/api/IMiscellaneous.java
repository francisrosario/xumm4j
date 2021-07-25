package com.fl.xumm4j.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fl.xumm4j.jackson.Ping;

public interface IMiscellaneous {
    String PING_ENDPOINT = "https://xumm.app/api/v1/platform/ping";
    String CURRATED_ASSETS_ENDPOINT = "https://xumm.app/api/v1/platform/curated-assets";
    String TXID_ENDPOINT = "https://xumm.app/api/v1/platform/xrpl-tx/";
    String KYC_STATUS_ENDPOINT = "https://xumm.app/api/v1/platform/kyc-status/";
    String RATES_ENDPOINT = "https://xumm.app/api/v1/platform/rates/";

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

    /**
     * @return
     */
    String doPing();

    /**
     * @return
     */
    String getCuratedAssets();

    /**
     * @param currencyCode
     * @return
     */
    String getRates(String currencyCode);

    /**
     * @param accountAddress
     * @return
     */
    String getKycStatus(String accountAddress);

    /**
     * @param txHash
     * @return
     */
    String getTransaction(String txHash);
    //To do App-Storage

    String postPayload(String txJson);

    Ping deserializerPing(String json) throws JsonProcessingException;
}
