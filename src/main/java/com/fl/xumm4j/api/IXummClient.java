package com.fl.xumm4j.api;

public interface IXummClient {
    String ENDPOINT_PING = "https://xumm.app/api/v1/platform/ping";
    String ENDPOINT_CURATED_ASSETS = "https://xumm.app/api/v1/platform/curated-assets/";
    String ENDPOINT_TXID = "https://xumm.app/api/v1/platform/xrpl-tx/";
    String ENDPOINT_KYC_STATUS = "https://xumm.app/api/v1/platform/kyc-status/";
    String ENDPOINT_KYC_STATUS_PUBLIC = "https://xumm.app/api/v1/platform/kyc-status/";
    String ENDPOINT_RATES = "https://xumm.app/api/v1/platform/rates/";
    String ENDPOINT_PAYLOAD = "https://xumm.app/api/v1/platform/payload/";
    String ENDPOINT_STORE_APP_STORAGE = "https://xumm.app/api/v1/platform/app-storage/";
    String ENDPOINT_GET_APP_STORAGE = "https://xumm.app/api/v1/platform/app-storage/";
    String ENDPOINT_DELETE_APP_STORAGE = "https://xumm.app/api/v1/platform/app-storage";

    String doPing();
    String getCuratedAssets();
    String getRates(String currencyCode);
    String getKycStatus(String UserToken_ClassicAddress);
    String getTransaction(String txHash);
    String create(String payload);
}
