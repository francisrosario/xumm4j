package com.fl.xumm4j.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fl.xumm4j.api.IDeserialize;
import com.fl.xumm4j.dao.*;

import java.math.BigInteger;

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
        jsonNode.findPath("details").iterator().forEachRemaining(x -> curatedAssets.addDetails(x.toPrettyString()));

        return curatedAssets;
    }

    @Override
    public CuratedAssetsDAO.Details Details(String json) {
        final CuratedAssetsDAO.Details curatedAssets = new CuratedAssetsDAO.Details();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        curatedAssets.setId(jsonNode.findPath("id").asInt());
        curatedAssets.setName(jsonNode.findPath("name").asText());
        curatedAssets.setDomain(jsonNode.findPath("domain").asText());
        curatedAssets.setAvatar(jsonNode.findPath("avatar").asText());
        curatedAssets.setShortlist(jsonNode.findPath("shortlist").asInt());

        jsonNode.findPath("currencies").fieldNames().forEachRemaining(curatedAssets::addCurrenciesFieldNames);
        jsonNode.findPath("currencies").iterator().forEachRemaining(x -> curatedAssets.addCurrencies(x.toPrettyString()));

        return curatedAssets;
    }

    @Override
    public CuratedAssetsDAO.Details.Currencies Currencies(String json){
        final CuratedAssetsDAO.Details.Currencies curatedAssets = new CuratedAssetsDAO.Details.Currencies();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        curatedAssets.setId(jsonNode.findPath("id").asInt());
        curatedAssets.setIssuerId(jsonNode.findPath("issuer_id").asInt());
        curatedAssets.setIssuer(jsonNode.findPath("issuer").asText());
        curatedAssets.setCurrency(jsonNode.findPath("currency").asText());
        curatedAssets.setName(jsonNode.findPath("name").asText());
        curatedAssets.setAvatar(jsonNode.findPath("avatar").asText());
        curatedAssets.setShortlist(jsonNode.findPath("shortlist").asInt());

        return curatedAssets;
    }

    @Override
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

    @Override
    public KycStatusDAO.Public KycPublic(String json){
        final KycStatusDAO.Public kycPublicDAO = new KycStatusDAO.Public();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kycPublicDAO.setAccount(jsonNode.findPath("account").asText());
        kycPublicDAO.setKycApproved(jsonNode.findPath("kycApproved").asBoolean());

        return kycPublicDAO;
    }

    @Override
    public KycStatusDAO KycState(String json){
        final KycStatusDAO kycStateDAO = new KycStatusDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kycStateDAO.setKycStatus(jsonNode.findPath("kycStatus").asText());
        kycStateDAO.setPossibleStatuses(jsonNode.findPath("kycApproved").toPrettyString());

        return kycStateDAO;
    }

    @Override
    public GetPayloadDAO getPayload(String json){
        final GetPayloadDAO payloadDAO = new GetPayloadDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        payloadDAO.setExists(jsonNode.findPath("meta").findPath("exists").asBoolean());
        payloadDAO.setUuid(jsonNode.findPath("meta").findPath("uuid").asText());
        payloadDAO.setExists(jsonNode.findPath("meta").findPath("multisign").asBoolean());
        payloadDAO.setExists(jsonNode.findPath("meta").findPath("submit").asBoolean());
        payloadDAO.setDestination(jsonNode.findPath("meta").findPath("destination").asText());
        payloadDAO.setResolved_destination(jsonNode.findPath("meta").findPath("resolved_destination").asText());
        payloadDAO.setResolved(jsonNode.findPath("meta").findPath("resolved").asBoolean());
        payloadDAO.setSigned(jsonNode.findPath("meta").findPath("signed").asBoolean());
        payloadDAO.setCancelled(jsonNode.findPath("meta").findPath("cancelled").asBoolean());
        payloadDAO.setExpired(jsonNode.findPath("meta").findPath("expired").asBoolean());
        payloadDAO.setPushed(jsonNode.findPath("meta").findPath("pushed").asBoolean());
        payloadDAO.setApp_opened(jsonNode.findPath("meta").findPath("app_opened").asBoolean());
        payloadDAO.setOpened_by_deeplink(jsonNode.findPath("meta").findPath("opened_by_deeplink").asText());
        payloadDAO.setReturn_url_app(jsonNode.findPath("meta").findPath("return_url_app").asText());
        payloadDAO.setGetReturn_url_web(jsonNode.findPath("meta").findPath("return_url_web").asText());
        payloadDAO.setIs_xapp(jsonNode.findPath("meta").findPath("is_xapp").asBoolean());

        payloadDAO.setPayload(jsonNode.findPath("payload").toPrettyString());
        payloadDAO.setResponse(jsonNode.findPath("response").toPrettyString());
        payloadDAO.setCustom_meta(jsonNode.findPath("custom_meta").toPrettyString());

        payloadDAO.setName(jsonNode.findPath("application").findPath("name").asText());
        payloadDAO.setDescription(jsonNode.findPath("application").findPath("description").asText());
        payloadDAO.setDisabled(jsonNode.findPath("application").findPath("disabled").asInt());
        payloadDAO.setUuidv4(jsonNode.findPath("application").findPath("uuidv4").asText());
        payloadDAO.setIcon_url(jsonNode.findPath("application").findPath("icon_url").asText());
        payloadDAO.setIssued_user_token(jsonNode.findPath("application").findPath("issued_user_token").asText());

        return payloadDAO;
    }

    @Override
    public PostPayloadDAO Payload(String json){
        final PostPayloadDAO postPayloadDAO = new PostPayloadDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        postPayloadDAO.setUuid(jsonNode.findPath("uuid").asText());
        postPayloadDAO.setAlways(jsonNode.findPath("next").findPath("always").asText());
        postPayloadDAO.setNo_push_msg_received(jsonNode.findPath("next").findPath("no_push_msg_received").asText());
        postPayloadDAO.setQr_png(jsonNode.findPath("refs").findPath("qr_png").asText());
        postPayloadDAO.setQr_matrix(jsonNode.findPath("refs").findPath("qr_matrix").asText());
        postPayloadDAO.setWebsocket_status(jsonNode.findPath("refs").findPath("websocket_status").asText());
        postPayloadDAO.setPushed(jsonNode.findPath("pushed").asBoolean());

        return postPayloadDAO;
    }

    @Override
    public RatesDAO Rates(String json){
        final RatesDAO ratesDAO = new RatesDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        ratesDAO.setUsd(jsonNode.findPath("USD").asText());
        ratesDAO.setXrp(jsonNode.findPath("XRP").asText());
        ratesDAO.setEn(jsonNode.findPath("__meta").findPath("currency").findPath("en").asText());
        ratesDAO.setCode(jsonNode.findPath("__meta").findPath("currency").findPath("code").asText());
        ratesDAO.setSymbol(jsonNode.findPath("__meta").findPath("currency").findPath("symbol").asText());

        return ratesDAO;
    }

    @Override
    public DeletePayloadDAO deletePayload(String json){
        DeletePayloadDAO deletepayloadDAO = new DeletePayloadDAO();
        try {
            jsonNode = mapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        deletepayloadDAO.setCancelled(jsonNode.findPath("result").findPath("cancelled").asBoolean());
        deletepayloadDAO.setReason(jsonNode.findPath("result").findPath("reason").asText());
        deletepayloadDAO.setMeta((jsonNode.findPath("meta").toPrettyString()));

        return deletepayloadDAO;
    }
}
