package com.fl.xumm4j.jackson;


import java.util.ArrayList;
import java.util.function.Consumer;

public class CuratedAssets {
    ArrayList<String> issuers = new ArrayList<>();
    ArrayList<String> currencies = new ArrayList<>();

    public void addIssuer(int index, String element) {
        issuers.add(index, element);
    }

    public void forEachIssuer(Consumer<? super String> action) {
        issuers.forEach(action);
    }

    public String getIssuer(int index) {
        return issuers.get(index);
    }

    public String get(int index) {
        return currencies.get(index);
    }

    public void addCurrencies(int index, String element) {
        currencies.add(index, element);
    }

    public void forEachCurrencies(Consumer<? super String> action) {
        currencies.forEach(action);
    }
}
