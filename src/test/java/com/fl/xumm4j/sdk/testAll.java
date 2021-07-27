package com.fl.xumm4j.sdk;

import com.fl.xumm4j.dao.CuratedAssetsDAO;
import com.fl.xumm4j.dao.PingDAO;
import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class testAll {
    CredentialsBuilder credentialsBuilder;
    XummClient xummclient;
    Deserialize deserialize;

    public testAll() {
        String apiKey = "7208fca5-4ac3-4638-b006-897dfcc0ab29";
        String secretKey = "6dab854e-b317-47f7-8453-490b8bd171ad";
        credentialsBuilder = new CredentialsBuilder.builder()
                .apiKey("7208fca5-4ac3-4638-b006-897dfcc0ab29")
                .secretKey("6dab854e-b317-47f7-8453-490b8bd171ad")
                .build();
        assertEquals(apiKey, credentialsBuilder.getApiKey());
        assertEquals(secretKey, credentialsBuilder.getSecretKey());
        xummclient = new XummClient(credentialsBuilder);
        deserialize = new Deserialize();
    }

    @Test
    void doPing() {
        String doPingResponse = xummclient.doPing();
        assertNotNull(doPingResponse);
    }

    @Test
    void getCuratedAssets() {
        String getCuratedAssets = xummclient.getCuratedAssets();
        assertNotNull(getCuratedAssets);
    }

    @Test
    void getRates() {
        String expectedRatesResponse = "{\n" +
                "  \"USD\" : 1,";
        String getRatesResponse = xummclient.getRates("USD");
        assertTrue(getRatesResponse.contains(expectedRatesResponse));
    }

    /**
    @Test
    void getKycStatus() {
    }
    **/

    @Test
    void getTransaction() {
        String expectedTransactionResponse = "{\n" +
                "  \"txid\" : \"DA66B07C9FE0876A3447DE4C57D565FC9C5324485912D10B48C0507F191A4021\",\n" +
                "  \"balanceChanges\" : {\n" +
                "    \"rDzTZxa7NwD9vmNf5dvTbW4FQDNSRsfPv6\" : [ {\n" +
                "      \"counterparty\" : \"\",\n" +
                "      \"currency\" : \"XRP\",\n" +
                "      \"value\" : \"-0.000012\",\n" +
                "      \"formatted\" : {\n" +
                "        \"value\" : \"-0.000012\",\n" +
                "        \"currency\" : \"XRP\"\n" +
                "      }\n" +
                "    } ]\n" +
                "  }";
        String getTransactionResponse = xummclient.getTransaction("DA66B07C9FE0876A3447DE4C57D565FC9C5324485912D10B48C0507F191A4021");
        assertNotNull(getTransactionResponse);
        assertTrue(getTransactionResponse.contains(expectedTransactionResponse));
    }


    @Test
    void deserializePing() {
        String JSON = xummclient.doPing();
        PingDAO ping = deserialize.Ping(JSON);
        assertNotNull(ping.getCall_uuidv4());
        assertNotNull(ping.getName());
        assertNotNull(ping.getUuidv4());
        assertNotNull(ping.getWebhookurl());
    }

    @Test
    void deserializeCuratedAssets() {
        String JSON = xummclient.getCuratedAssets();
        CuratedAssetsDAO curatedAssets = deserialize.CuratedAssets(JSON);
        assertNotNull(curatedAssets.getCurrencies(3));
        assertNotNull(curatedAssets.getDetails(3));
        assertNotNull(curatedAssets.getIssuer(3));
    }
}