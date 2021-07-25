package com.fl.xumm4j.jackson;


import java.util.ArrayList;
import java.util.function.Consumer;

public class CuratedAssets {
    private ArrayList<String> issuers = new ArrayList<>();
    private ArrayList<String> currencies = new ArrayList<>();
    private ArrayList<String> details = new ArrayList<>();

    public void addIssuer(int index, String element) {
        issuers.add(index, element);
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

    public void addCurrencies(int index, String element) {
        currencies.add(index, element);
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

    public boolean addDetails(String s) {
        return details.add(s);
    }

    public int detailsSize() {
        return details.size();
    }
}
