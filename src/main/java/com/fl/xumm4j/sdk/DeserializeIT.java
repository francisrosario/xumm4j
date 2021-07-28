package com.fl.xumm4j.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xumm4j.api.IDeserialize;
import com.fl.xumm4j.dao.*;

public class DeserializeIT implements IDeserialize {
    private final ObjectMapper mapper;
    private JsonNode jsonNode;

    public DeserializeIT() {
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
        jsonNode.findPath("details").iterator().forEachRemaining(x -> curatedAssets.addDetails(x.toPrettyString()));

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

    public StorageDAO Storage(String json){
        final StorageDAO storageDAO = new StorageDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        storageDAO.setName(jsonNode.findPath("application").findPath("name").asText());
        storageDAO.setUuidv4(jsonNode.findPath("application").findPath("uuidv4").asText());
        storageDAO.setStored(jsonNode.findPath("stored").asBoolean());
        storageDAO.setData(jsonNode.findPath("data").toPrettyString());

        return storageDAO;
    }

    public KycPublicDAO KycPublic(String json){
        final KycPublicDAO kycPublicDAO = new KycPublicDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kycPublicDAO.setAccount(jsonNode.get("account").asText());
        kycPublicDAO.setKycApproved(jsonNode.get("kycApproved").asBoolean());

        return kycPublicDAO;
    }

    public KycStateDAO KycState(String json){
        final KycStateDAO kycStateDAO = new KycStateDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kycStateDAO.setKycStatus(jsonNode.get("kycStatus").asText());
        kycStateDAO.setPossibleStatuses(jsonNode.get("kycApproved").toPrettyString());

        return kycStateDAO;
    }
}
