package com.fl.xumm4j.dao;

import java.math.BigInteger;

public class RatesDAO {
    private BigInteger usd;
    private BigInteger xrp;
    private String en;
    private String code;
    private String symbol;

    public BigInteger getUsd() {
        return usd;
    }

    public void setUsd(BigInteger usd) {
        this.usd = usd;
    }

    public BigInteger getXrp() {
        return xrp;
    }

    public void setXrp(BigInteger xrp) {
        this.xrp = xrp;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
