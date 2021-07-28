package com.fl.xumm4j.dao;

public class KycPublicDAO {
    private String account;
    private boolean kycApproved;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public boolean isKycApproved() {
        return kycApproved;
    }

    public void setKycApproved(boolean kycApproved) {
        this.kycApproved = kycApproved;
    }
}
