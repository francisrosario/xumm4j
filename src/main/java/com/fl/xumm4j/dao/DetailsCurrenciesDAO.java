package com.fl.xumm4j.dao;

public class DetailsCurrenciesDAO {
    private int id;
    private int issuerId;
    private String issuer;
    private String currency;
    private String name;
    private String avatar;
    private int shortlist;

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(int issuerId) {
        this.issuerId = issuerId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getShortlist() {
        return shortlist;
    }

    public void setShortlist(int shortlist) {
        this.shortlist = shortlist;
    }
}
