package com.fl.xumm4j.jackson;


import java.util.ArrayList;
import java.util.function.Consumer;

public class CuratedAssets {
    private ArrayList<String> issuers = new ArrayList<>();
    private ArrayList<String> currencies = new ArrayList<>();
    private ArrayList<String> details = new ArrayList<>();


    public void addIssuer(String s) {
        issuers.add(s);
    }

    public void forEachIssuer(Consumer<? super String> action) {
        issuers.forEach(action);
    }

    public String getIssuer(int index) {
        return issuers.get(index);
    }

    public int issuerSize() {
        return issuers.size();
    }

    public String getCurrencies(int index) {
        return currencies.get(index);
    }

    public void addCurrencies(String s) {
        currencies.add(s);
    }

    public void forEachCurrencies(Consumer<? super String> action) {
        currencies.forEach(action);
    }

    public int currenciesSize() {
        return currencies.size();
    }

    public String getDetails(int index) {
        return details.get(index);
    }

    public void forEachDetails(Consumer<? super String> action) {
        details.forEach(action);
    }

    public void addDetails(String s) {
        details.add(s);
    }

    public int detailsSize() {
        return details.size();
    }
}
