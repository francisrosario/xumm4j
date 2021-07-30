package com.fl.xumm4j.dao;

public class KycStatusDAO {
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

    public static class Public{
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
}
