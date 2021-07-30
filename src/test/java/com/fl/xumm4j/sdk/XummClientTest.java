package com.fl.xumm4j.sdk;

import com.fl.xumm4j.dao.CuratedAssetsDAO;
import com.fl.xumm4j.dao.PingDAO;
import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class XummClientTest {
    CredentialsBuilder credentialsBuilder;
    XummClient xummclient;
    DeserializeIT deserialize;

    public XummClientTest() {
        //These Keys aren't used for production, only in TESTING and DEMONSTRATION purposes.
        credentialsBuilder = new CredentialsBuilder.builder()
                .apiKey("7208fca5-4ac3-4638-b006-897dfcc0ab29")
                .secretKey("6dab854e-b317-47f7-8453-490b8bd171ad")
                .build();
        assertEquals("7208fca5-4ac3-4638-b006-897dfcc0ab29", credentialsBuilder.getApiKey());
        assertEquals("6dab854e-b317-47f7-8453-490b8bd171ad", credentialsBuilder.getSecretKey());

        xummclient = new XummClient(credentialsBuilder);
        deserialize = new DeserializeIT();
    }

    @Test
    void doPing() {
        String doPingResponse = xummclient.doPing();
        assertTrue(doPingResponse.contains("{\n" +
                "  \"pong\" : true,\n" +
                "  \"auth\" : {\n" +
                "    \"quota\" : { },\n" +
                "    \"application\" : {\n" +
                "      \"uuidv4\" : \"7208fca5-4ac3-4638-b006-897dfcc0ab29\",\n" +
                "      \"name\" : \"xumm4j sandbox\",\n" +
                "      \"webhookurl\" : \"http://localhost:8080\",\n" +
                "      \"disabled\" : 0\n" +
                "    },"));
    }

    @Test
    void getCuratedAssets() {
        String getCuratedAssets = xummclient.getCuratedAssets();
        assertTrue(getCuratedAssets.contains("\"details\" : {\n" +
                "    \"Bitstamp\" : {\n" +
                "      \"id\" : 185,\n" +
                "      \"name\" : \"Bitstamp\",\n" +
                "      \"domain\" : \"bitstamp.net\","));
    }

    @Test
    void getRates() {
        String getRatesResponse = xummclient.getRates("USD");
        assertTrue(getRatesResponse.contains("{\n" +
                "  \"USD\" : 1,"));
    }

    @Test
    void getKycPublic() {
        String getKYCResponse = xummclient.getKycStatus("rDWLGshgAxSX2G4TEv3gA6QhtLgiXrWQXB");
        assertEquals(String.valueOf(true), getKYCResponse);
    }

    @Test
    void getTransaction() {
        String getTransactionResponse = xummclient.getTransaction("DA66B07C9FE0876A3447DE4C57D565FC9C5324485912D10B48C0507F191A4021");
        assertTrue(getTransactionResponse.contains("{\n" +
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
                "  }"));
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
        assertNotNull(curatedAssets.getCurrencies(0));
    }
}