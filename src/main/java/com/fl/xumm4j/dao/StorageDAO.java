package com.fl.xumm4j.dao;

public class StorageDAO {
    private String name;
    private String uuidv4;
    private String data;
    private boolean stored;

    public boolean isStored() {
        return stored;
    }

    public void setStored(boolean stored) {
        this.stored = stored;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuidv4() {
        return uuidv4;
    }

    public void setUuidv4(String uuidv4) {
        this.uuidv4 = uuidv4;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
