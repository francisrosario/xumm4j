package com.fl.xumm4j.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xumm4j.dao.DetailsCurrenciesDAO;
import com.fl.xumm4j.dao.DetailsDAO;
import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import com.fl.xumm4j.api.IMiscellaneous;
import com.fl.xumm4j.dao.CuratedAssetsDAO;
import com.fl.xumm4j.dao.PingDAO;

import java.io.IOException;
import java.util.Objects;

public class Misc implements IMiscellaneous {
    private final Http http;
    private final ObjectMapper mapper;
    private String response;
    private JsonNode jsonNode;

    public Misc(CredentialsBuilder iCredentials) {
        http = new Http(iCredentials);
        mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
    public PingDAO deserializePing(String json) {
        final PingDAO ping = new PingDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ping.setPong(jsonNode.findPath("pong").asBoolean());
        ping.setUuidv4(jsonNode.findPath("auth").findPath("application").findPath("uuidv4").asText());
        ping.setName(jsonNode.findPath("auth").findPath("application").findPath("name").asText());
        ping.setWebhookurl(jsonNode.findPath("auth").findPath("application").findPath("webhookurl").asText());
        ping.setDisabled(jsonNode.findPath("auth").findPath("application").findPath("disabled").asInt());
        ping.setCall_uuidv4(jsonNode.findPath("auth").findPath("call").findPath("uuidv4").asText());

        return ping;
    }

    //////////////
    //Deserializer

    @Override
    public CuratedAssetsDAO deserializeCuratedAssets(String json) {
        final CuratedAssetsDAO curatedAssets = new CuratedAssetsDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        jsonNode.withArray("issuers").elements().forEachRemaining(x -> curatedAssets.addIssuer(x.asText()));
        jsonNode.withArray("currencies").elements().forEachRemaining(x -> curatedAssets.addCurrencies(x.asText()));
        jsonNode.findPath("details").iterator().forEachRemaining(x -> curatedAssets.addDetails(x.toString()));

        return curatedAssets;
    }

    @Override
    public DetailsDAO deserializeDetails(String json) {
        final DetailsDAO detailsDAO = new DetailsDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        detailsDAO.setId(jsonNode.findPath("id").asInt());
        detailsDAO.setName(jsonNode.findPath("name").asText());
        detailsDAO.setDomain(jsonNode.findPath("domain").asText());
        detailsDAO.setAvatar(jsonNode.findPath("avatar").asText());
        detailsDAO.setShortlist(jsonNode.findPath("shortlist").asInt());

        jsonNode.findPath("currencies").fieldNames().forEachRemaining(detailsDAO::addCurrenciesFieldNames);
        jsonNode.findPath("currencies").iterator().forEachRemaining(x -> detailsDAO.addCurrencies(x.toPrettyString()));

        return detailsDAO;
    }

    @Override
    public DetailsCurrenciesDAO deserializeDetailsCurrencies(String json){
        final DetailsCurrenciesDAO DetailsCurrenciesDAO = new DetailsCurrenciesDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        DetailsCurrenciesDAO.setId(jsonNode.findPath("id").asInt());
        DetailsCurrenciesDAO.setIssuerId(jsonNode.findPath("issuer_id").asInt());
        DetailsCurrenciesDAO.setIssuer(jsonNode.findPath("issuer").asText());
        DetailsCurrenciesDAO.setCurrency(jsonNode.findPath("currency").asText());
        DetailsCurrenciesDAO.setName(jsonNode.findPath("name").asText());
        DetailsCurrenciesDAO.setAvatar(jsonNode.findPath("avatar").asText());
        DetailsCurrenciesDAO.setShortlist(jsonNode.findPath("shortlist").asInt());

        return DetailsCurrenciesDAO;
    }
}
