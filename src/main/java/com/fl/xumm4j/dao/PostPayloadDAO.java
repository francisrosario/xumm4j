package com.fl.xumm4j.dao;

public class PostPayloadDAO {
    private String uuid;
    private String always;
    private String no_push_msg_received;
    private String qr_png;
    private String qr_matrix;
    private String websocket_status;
    private boolean pushed;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAlways() {
        return always;
    }

    public void setAlways(String always) {
        this.always = always;
    }

    public String getNo_push_msg_received() {
        return no_push_msg_received;
    }

    public void setNo_push_msg_received(String no_push_msg_received) {
        this.no_push_msg_received = no_push_msg_received;
    }

    public String getQr_png() {
        return qr_png;
    }

    public void setQr_png(String qr_png) {
        this.qr_png = qr_png;
    }

    public String getQr_matrix() {
        return qr_matrix;
    }

    public void setQr_matrix(String qr_matrix) {
        this.qr_matrix = qr_matrix;
    }

    public String getWebsocket_status() {
        return websocket_status;
    }

    public void setWebsocket_status(String websocket_status) {
        this.websocket_status = websocket_status;
    }

    public boolean isPushed() {
        return pushed;
    }

    public void setPushed(boolean pushed) {
        this.pushed = pushed;
    }
}
