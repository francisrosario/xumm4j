package com.fl.xumm4j.sdk;

import com.fl.xumm4j.jackson.CuratedAssetsDAO;
import com.fl.xumm4j.jackson.PingDAO;
import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MiscTest {
    CredentialsBuilder credentialsBuilder;
    Misc misc;
    public MiscTest() {
        String apiKey = "7208fca5-4ac3-4638-b006-897dfcc0ab29";
        String secretKey = "6dab854e-b317-47f7-8453-490b8bd171ad";
        credentialsBuilder = new CredentialsBuilder.builder()
                .apiKey("7208fca5-4ac3-4638-b006-897dfcc0ab29")
                .secretKey("6dab854e-b317-47f7-8453-490b8bd171ad")
                .build();
        assertEquals(apiKey, credentialsBuilder.getApiKey());
        assertEquals(secretKey, credentialsBuilder.getSecretKey());

        misc = new Misc(credentialsBuilder);
    }

    @Test
    void doPing() {
        String expectedPingResponse = "{\n" +
                "  \"pong\" : true,\n" +
                "  \"auth\" : {\n" +
                "    \"quota\" : { },\n" +
                "    \"application\" : {\n" +
                "      \"uuidv4\" : \"7208fca5-4ac3-4638-b006-897dfcc0ab29\",\n" +
                "      \"name\" : \"XUMM4J\",\n" +
                "      \"webhookurl\" : \"http://localhost:8080\",\n" +
                "      \"disabled\" : 0\n" +
                "    }";
        String doPingResponse = misc.doPing();
        assertTrue(doPingResponse.contains(expectedPingResponse));
    }

    @Test
    void getCuratedAssets() {
        String getCuratedAssets = misc.getCuratedAssets();
        assertNotNull(getCuratedAssets);
    }

    @Test
    void getRates() {
        String expectedRatesResponse = "{\n" +
                "  \"USD\" : 1,";
        String getRatesResponse = misc.getRates("USD");
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
        String getTransactionResponse = misc.getTransaction("DA66B07C9FE0876A3447DE4C57D565FC9C5324485912D10B48C0507F191A4021");
        assertTrue(getTransactionResponse.contains(expectedTransactionResponse));
    }


    @Test
    void deserializePing() {
        String JSON = misc.doPing();
        PingDAO ping = misc.deserializePing(JSON);
        assertNotNull(ping.getCall_uuidv4());
        assertNotNull(ping.getName());
        assertNotNull(ping.getUuidv4());
        assertNotNull(ping.getWebhookurl());
    }

    @Test
    void deserializeCuratedAssets() {
        String JSON = misc.getCuratedAssets();
        CuratedAssetsDAO curatedAssets = misc.deserializeCuratedAssets(JSON);
        assertNotNull(curatedAssets.getCurrencies(0));
        assertNotNull(curatedAssets.getDetails(0));
        assertNotNull(curatedAssets.getIssuer(0));
    }
}