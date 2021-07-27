package com.fl.xumm4j.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xumm4j.api.IDeserialize;
import com.fl.xumm4j.dao.CuratedAssetsDAO;
import com.fl.xumm4j.dao.CurrenciesDAO;
import com.fl.xumm4j.dao.DetailsDAO;
import com.fl.xumm4j.dao.PingDAO;

public class Deserialize implements IDeserialize {
    private final ObjectMapper mapper;
    private JsonNode jsonNode;

    public Deserialize() {
        mapper = new ObjectMapper();
    }

    @Override
    public PingDAO Ping(String json) {
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

    @Override
    public CuratedAssetsDAO CuratedAssets(String json) {
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
    public DetailsDAO Details(String json) {
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
    public CurrenciesDAO Currencies(String json){
        final CurrenciesDAO CurrenciesDAO = new CurrenciesDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        CurrenciesDAO.setId(jsonNode.findPath("id").asInt());
        CurrenciesDAO.setIssuerId(jsonNode.findPath("issuer_id").asInt());
        CurrenciesDAO.setIssuer(jsonNode.findPath("issuer").asText());
        CurrenciesDAO.setCurrency(jsonNode.findPath("currency").asText());
        CurrenciesDAO.setName(jsonNode.findPath("name").asText());
        CurrenciesDAO.setAvatar(jsonNode.findPath("avatar").asText());
        CurrenciesDAO.setShortlist(jsonNode.findPath("shortlist").asInt());

        return CurrenciesDAO;
    }

}
