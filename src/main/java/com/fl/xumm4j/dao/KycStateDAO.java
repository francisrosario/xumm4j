package com.fl.xumm4j.dao;

public class KycStateDAO {
    private String kycStatus;
    private String possibleStatuses;

    public String getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }

    public String getPossibleStatuses() {
        return possibleStatuses;
    }

    public void setPossibleStatuses(String possibleStatuses) {
        this.possibleStatuses = possibleStatuses;
    }
}
