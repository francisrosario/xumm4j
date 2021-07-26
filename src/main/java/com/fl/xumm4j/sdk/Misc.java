package com.fl.xumm4j.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xumm4j.dao.DetailsCurrenciesDAO;
import com.fl.xumm4j.dao.DetailsDAO;
import com.fl.xumm4j.sdk.builder.CredentialsBuilder;
import com.fl.xumm4j.api.IMiscellaneous;
import com.fl.xumm4j.dao.CuratedAssetsDAO;
import com.fl.xumm4j.dao.PingDAO;
import org.xrpl.xrpl4j.model.fl.jackson.ObjectMapperFactory;

import java.io.IOException;
import java.util.Objects;

public class Misc implements IMiscellaneous {
    private final Http http;
    private final ObjectMapper mapper;
    private String response;
    private JsonNode jsonNode;

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
    public PingDAO deserializePing(String json) {
        final PingDAO ping = new PingDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        ping.setPong(jsonNode.get("pong").asBoolean());
        ping.setUuidv4(jsonNode.get("auth").get("application").get("uuidv4").asText());
        ping.setName(jsonNode.get("auth").get("application").get("name").asText());
        ping.setWebhookurl(jsonNode.get("auth").get("application").get("webhookurl").asText());
        ping.setDisabled(jsonNode.get("auth").get("application").get("disabled").asInt());
        ping.setCall_uuidv4(jsonNode.get("auth").get("call").get("uuidv4").asText());

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
        jsonNode.get("details").iterator().forEachRemaining(x -> curatedAssets.addDetails(x.toString()));

        return curatedAssets;
    }

    public DetailsDAO deserializeDetails(String json) {
        final DetailsDAO detailsDAO = new DetailsDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        detailsDAO.setId(jsonNode.get("id").asInt());
        detailsDAO.setName(jsonNode.get("name").asText());
        detailsDAO.setDomain(jsonNode.get("domain").asText());
        detailsDAO.setAvatar(jsonNode.get("avatar").asText());
        detailsDAO.setShortlist(jsonNode.get("shortlist").asInt());

        jsonNode.get("currencies").fieldNames().forEachRemaining(detailsDAO::addCurrenciesFieldNames);
        jsonNode.get("currencies").iterator().forEachRemaining(x -> detailsDAO.addCurrencies(x.toPrettyString()));

        return detailsDAO;
    }

    public DetailsCurrenciesDAO deserializeDetailsCurrencies(String json){
        final DetailsCurrenciesDAO DetailsCurrenciesDAO = new DetailsCurrenciesDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        DetailsCurrenciesDAO.setId(jsonNode.get("id").asInt());
        DetailsCurrenciesDAO.setIssuerId(jsonNode.get("issuer_id").asInt());
        DetailsCurrenciesDAO.setIssuer(jsonNode.get("issuer").asText());
        DetailsCurrenciesDAO.setCurrency(jsonNode.get("currency").asText());
        DetailsCurrenciesDAO.setName(jsonNode.get("name").asText());
        DetailsCurrenciesDAO.setAvatar(jsonNode.get("avatar").asText());
        DetailsCurrenciesDAO.setShortlist(jsonNode.get("shortlist").asInt());

        return DetailsCurrenciesDAO;
    }
}
