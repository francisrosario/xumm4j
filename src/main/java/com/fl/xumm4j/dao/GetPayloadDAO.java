package com.fl.xumm4j.dao;

public class GetPayloadDAO {
    private boolean exists;
    private String uuid;
    private boolean multisign;
    private boolean submit;
    private String destination;
    private String resolved_destination;
    private boolean resolved;
    private boolean signed;
    private boolean cancelled;
    private boolean expired;
    private boolean pushed;
    private boolean app_opened;
    private String opened_by_deeplink;
    private String return_url_app;
    private String getReturn_url_web;
    private boolean is_xapp;
    private String name;
    private String description;
    private int disabled;
    private String uuidv4;
    private String icon_url;
    private String issued_user_token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }

    public String getUuidv4() {
        return uuidv4;
    }

    public void setUuidv4(String uuidv4) {
        this.uuidv4 = uuidv4;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getIssued_user_token() {
        return issued_user_token;
    }

    public void setIssued_user_token(String issued_user_token) {
        this.issued_user_token = issued_user_token;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCustom_meta() {
        return custom_meta;
    }

    public void setCustom_meta(String custom_meta) {
        this.custom_meta = custom_meta;
    }

    private String payload;
    private String response;
    private String custom_meta;

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isMultisign() {
        return multisign;
    }

    public void setMultisign(boolean multisign) {
        this.multisign = multisign;
    }

    public boolean isSubmit() {
        return submit;
    }

    public void setSubmit(boolean submit) {
        this.submit = submit;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getResolved_destination() {
        return resolved_destination;
    }

    public void setResolved_destination(String resolved_destination) {
        this.resolved_destination = resolved_destination;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public boolean isSigned() {
        return signed;
    }

    public void setSigned(boolean signed) {
        this.signed = signed;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isPushed() {
        return pushed;
    }

    public void setPushed(boolean pushed) {
        this.pushed = pushed;
    }

    public boolean isApp_opened() {
        return app_opened;
    }

    public void setApp_opened(boolean app_opened) {
        this.app_opened = app_opened;
    }

    public String getOpened_by_deeplink() {
        return opened_by_deeplink;
    }

    public void setOpened_by_deeplink(String opened_by_deeplink) {
        this.opened_by_deeplink = opened_by_deeplink;
    }

    public String getReturn_url_app() {
        return return_url_app;
    }

    public void setReturn_url_app(String return_url_app) {
        this.return_url_app = return_url_app;
    }

    public String getGetReturn_url_web() {
        return getReturn_url_web;
    }

    public void setGetReturn_url_web(String getReturn_url_web) {
        this.getReturn_url_web = getReturn_url_web;
    }

    public boolean isIs_xapp() {
        return is_xapp;
    }

    public void setIs_xapp(boolean is_xapp) {
        this.is_xapp = is_xapp;
    }
}
