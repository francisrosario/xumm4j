package com.fl.xumm4j.dao;


import java.util.ArrayList;
import java.util.function.Consumer;

public class GetCuratedAssetsDAO {
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

    public static class Details{
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

        public static class Currencies{
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
    }
}
