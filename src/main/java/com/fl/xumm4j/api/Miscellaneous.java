package com.fl.xumm4j.api;

public interface Miscellaneous {
    String PING_ENDPOINT = "https://xumm.app/api/v1/platform/ping";
    String CURRATED_ASSETS_ENDPOINT = "https://xumm.app/api/v1/platform/curated-assets";
    String TXID_ENDPOINT = "https://xumm.app/api/v1/platform/xrpl-tx/";
    String KYC_STATUS_ENDPOINT = "https://xumm.app/api/v1/platform/kyc-status/";

    String doPing();
    String getCurratedAssets();
    String getKycStatus(String accountAddress);
}
