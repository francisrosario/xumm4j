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
    CuratedAssets curatedAssets;
    Ping p;

    public Misc(CredentialsBuilder iCredentials) {
        http = new Http(iCredentials);
        curatedAssets = new CuratedAssets();
        p = new Ping();
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
        jsonNode = mapper.readTree(json);

        p.setPong(jsonNode.get("pong").asBoolean());
        p.setUuidv4(jsonNode.get("auth").get("application").get("uuidv4").asText());
        p.setName(jsonNode.get("auth").get("application").get("name").asText());
        p.setWebhookurl(jsonNode.get("auth").get("application").get("webhookurl").asText());
        p.setDisabled(jsonNode.get("auth").get("application").get("disabled").asInt());
        p.setCall_uuidv4(jsonNode.get("auth").get("call").get("uuidv4").asText());

        return p;
    }

    public CuratedAssets deserializeCuratedAssets(String json) throws JsonProcessingException {
        jsonNode = mapper.readTree(json);

        jsonNode.withArray("issuers").iterator().forEachRemaining(x -> curatedAssets.addIssuer(x.asText()));
        jsonNode.withArray("currencies").iterator().forEachRemaining(x -> curatedAssets.addCurrencies(x.asText()));
        jsonNode.get("details").iterator().forEachRemaining(x -> curatedAssets.addDetails(x.toString()));

        return curatedAssets;
    }
}
