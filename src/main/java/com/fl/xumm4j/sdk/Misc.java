package com.fl.xumm4j.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import com.fl.xumm4j.api.IMiscellaneous;
import com.fl.xumm4j.jackson.CuratedAssets;
import com.fl.xumm4j.jackson.Ping;
import org.xrpl.xrpl4j.model.fl.jackson.ObjectMapperFactory;

import java.io.IOException;
import java.util.Objects;

public class Misc implements IMiscellaneous {
    Http http;
    ObjectMapper mapper;
    String response;
    JsonNode jsonNode;

    public Misc(CredentialsBuilder iCredentials) {
        http = new Http(iCredentials);
        mapper = ObjectMapperFactory.create()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
    }

    private String getToPrettyString(String response) throws JsonProcessingException {
        return mapper.readTree(response).toPrettyString();
    }

    @Override
    public String doPing() {
        try {
            response = Objects.requireNonNull(http.doGet(PING_ENDPOINT).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String getCuratedAssets() {
        try {
            response = Objects.requireNonNull(http.doGet(CURRATED_ASSETS_ENDPOINT).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String getRates(String currencyCode) {
        try {
            response = Objects.requireNonNull(http.doGet(RATES_ENDPOINT+currencyCode).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String getKycStatus(String accountAddress) {
        try {
            response = Objects.requireNonNull(http.doGet(KYC_STATUS_ENDPOINT+accountAddress).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String getTransaction(String txHash) {
        try {
            response = Objects.requireNonNull(http.doGet(TXID_ENDPOINT+txHash).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String postPayload(String txJson) {
        try {
            response = Objects.requireNonNull(http.doPost(txJson).body()).string();
            response = getToPrettyString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Ping deserializerPing(String json) throws JsonProcessingException {
        Ping ping = new Ping();
        jsonNode = mapper.readTree(json);

        ping.setPong(jsonNode.get("pong").asBoolean());
        ping.setUuidv4(jsonNode.get("auth").get("application").get("uuidv4").asText());
        ping.setName(jsonNode.get("auth").get("application").get("name").asText());
        ping.setWebhookurl(jsonNode.get("auth").get("application").get("webhookurl").asText());
        ping.setDisabled(jsonNode.get("auth").get("application").get("disabled").asInt());
        ping.setCall_uuidv4(jsonNode.get("auth").get("call").get("uuidv4").asText());

        return ping;
    }

    public CuratedAssets deserializeCuratedAssets(String json) throws JsonProcessingException {
        CuratedAssets curatedAssets = new CuratedAssets();
        jsonNode = mapper.readTree(json);

        jsonNode.withArray("issuers").elements().forEachRemaining(a -> curatedAssets.addIssuer(a.asText()));
        jsonNode.withArray("currencies").elements().forEachRemaining(b -> curatedAssets.addCurrencies(b.asText()));
        jsonNode.get("details").iterator().forEachRemaining(c -> curatedAssets.addDetails(c.toString()));

        return curatedAssets;
    }
}
