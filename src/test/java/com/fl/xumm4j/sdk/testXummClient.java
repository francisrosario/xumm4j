package com.fl.xumm4j.sdk;

import com.fl.xumm4j.dao.CuratedAssetsDAO;
import com.fl.xumm4j.dao.PingDAO;
import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class testXummClient {
    CredentialsBuilder credentialsBuilder;
    XummClient xummclient;
    DeserializeIT deserialize;

    public testXummClient() {
        //These Keys aren't used for production only in TESTING purposes.
        String ExpectedApiKey = "7208fca5-4ac3-4638-b006-897dfcc0ab29";
        String ExptectedSecretKey = "6dab854e-b317-47f7-8453-490b8bd171ad";

        credentialsBuilder = new CredentialsBuilder.builder()
                .apiKey("7208fca5-4ac3-4638-b006-897dfcc0ab29")
                .secretKey("6dab854e-b317-47f7-8453-490b8bd171ad")
                .build();
        assertEquals(ExpectedApiKey, credentialsBuilder.getApiKey());
        assertEquals(ExptectedSecretKey, credentialsBuilder.getSecretKey());

        xummclient = new XummClient(credentialsBuilder);
        deserialize = new DeserializeIT();
    }

    @Test
    void doPing() {
        String expectedPingResponse = "{\n" +
                "  \"pong\" : true,\n" +
                "  \"auth\" : {\n" +
                "    \"quota\" : { },\n" +
                "    \"application\" : {\n" +
                "      \"uuidv4\" : \"7208fca5-4ac3-4638-b006-897dfcc0ab29\",\n" +
                "      \"name\" : \"xumm4j sandbox\",\n" +
                "      \"webhookurl\" : \"http://localhost:8080\",\n" +
                "      \"disabled\" : 0\n" +
                "    },";
        String doPingResponse = xummclient.doPing();
        assertTrue(doPingResponse.contains(expectedPingResponse));
    }

    @Test
    void getCuratedAssets() {
        String expectedCuratedAssetsResponse = "\"details\" : {\n" +
                "    \"Bitstamp\" : {\n" +
                "      \"id\" : 185,\n" +
                "      \"name\" : \"Bitstamp\",\n" +
                "      \"domain\" : \"bitstamp.net\",";
        String getCuratedAssets = xummclient.getCuratedAssets();
        assertTrue(getCuratedAssets.contains(expectedCuratedAssetsResponse));
    }

    @Test
    void getRates() {
        String expectedRatesResponse = "{\n" +
                "  \"USD\" : 1,";
        String getRatesResponse = xummclient.getRates("USD");
        assertTrue(getRatesResponse.contains(expectedRatesResponse));
    }

    @Test
    void getKycPublic() {
        String expectedKYCResponse = "{\n" +
                "  \"account\" : \"rDWLGshgAxSX2G4TEv3gA6QhtLgiXrWQXB\",\n" +
                "  \"kycApproved\" : true\n" +
                "}";
        String getKYCResponse = xummclient.getKycStatus("rDWLGshgAxSX2G4TEv3gA6QhtLgiXrWQXB");
        assertEquals(expectedKYCResponse, getKYCResponse);
    }

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
        assertTrue(getTransactionResponse.contains(expectedTransactionResponse));
    }

    ///////////
    //Temp. deserializeTest zone
    @Test
    void deserializePing() {
        String JSON = xummclient.doPing();
        PingDAO ping = deserialize.Ping(JSON);
        assertEquals("http://localhost:8080", ping.getWebhookurl());
    }

    @Test
    void deserializeCuratedAssets() {
        String JSON = xummclient.getCuratedAssets();
        CuratedAssetsDAO curatedAssets = deserialize.CuratedAssets(JSON);
        assertEquals("USD", curatedAssets.getCurrencies(0));
    }
}