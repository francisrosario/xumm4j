package com.fl.xumm4j.api;

public interface IXummClient {
    String ENDPOINT_PING = "https://xumm.app/api/v1/platform/ping";
    String ENDPOINT_CURATED_ASSETS = "https://xumm.app/api/v1/platform/curated-assets/";
    String ENDPOINT_TXID = "https://xumm.app/api/v1/platform/xrpl-tx/";
    String ENDPOINT_KYC_STATUS = "https://xumm.app/api/v1/platform/kyc-status/";
    String ENDPOINT_KYC_STATUS_PUBLIC = "https://xumm.app/api/v1/platform/kyc-status/";
    String ENDPOINT_RATES = "https://xumm.app/api/v1/platform/rates/";
    String ENDPOINT_PAYLOAD = "https://xumm.app/api/v1/platform/payload/";

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
    String getKycStatus(String UserToken);

    /**
     * @param txHash
     * @return
     */
    String getTransaction(String txHash);
    //To do App-Storage

    /**
     * @param txJson
     * @return
     */
    String create(String txJson);
}
