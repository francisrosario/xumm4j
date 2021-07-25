package com.fl.xumm4j.api;

public interface IMiscellaneous {
    String PING_ENDPOINT = "https://xumm.app/api/v1/platform/ping";
    String CURRATED_ASSETS_ENDPOINT = "https://xumm.app/api/v1/platform/curated-assets";
    String TXID_ENDPOINT = "https://xumm.app/api/v1/platform/xrpl-tx/";
    String KYC_STATUS_ENDPOINT = "https://xumm.app/api/v1/platform/kyc-status/";
    String RATES_ENDPOINT = "https://xumm.app/api/v1/platform/rates/";

    /**
     * @return
     */
    String doPing();

    /**
     * @return
     */
    String getCurratedAssets();

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
}