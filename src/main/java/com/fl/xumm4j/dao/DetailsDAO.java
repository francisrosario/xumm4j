package com.fl.xumm4j.dao;

import java.util.ArrayList;
import java.util.function.Consumer;

public class DetailsDAO {
    private int id;
    private String name;
    private String domain;
    private String avatar;
    private int shortlist;

    private ArrayList<String> currenciesFieldNames = new ArrayList<>();
    private ArrayList<String> currencies = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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

    public boolean addCurrenciesFieldNames(String s) {
        return currenciesFieldNames.add(s);
    }

    public void forEachCurrenciesFieldNames(Consumer<? super String> action) {
        currenciesFieldNames.forEach(action);
    }

    public String getCurrenciesFieldNames(int index) {
        return currenciesFieldNames.get(index);
    }

    public boolean addCurrencies(String s) {
        return currencies.add(s);
    }

    public void forEachCurrencies(Consumer<? super String> action) {
        currencies.forEach(action);
    }

    public String getCurrencies(int index) {
        return currencies.get(index);
    }

}
