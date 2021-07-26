package com.fl.xumm4j.jackson;

public class PingDAO {
    private boolean pong;
    private String uuidv4;
    private String name;
    private String webhookurl;
    private int disabled;
    private String call_uuidv4;

    public boolean isPong() {
        return pong;
    }

    public void setPong(boolean pong) {
        this.pong = pong;
    }

    public String getUuidv4() {
        return uuidv4;
    }

    public void setUuidv4(String uuidv4) {
        this.uuidv4 = uuidv4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebhookurl() {
        return webhookurl;
    }

    public void setWebhookurl(String webhookurl) {
        this.webhookurl = webhookurl;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }

    public String getCall_uuidv4() {
        return call_uuidv4;
    }

    public void setCall_uuidv4(String call_uuidv4) {
        this.call_uuidv4 = call_uuidv4;
    }
}
