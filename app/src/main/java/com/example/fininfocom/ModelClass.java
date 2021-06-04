package com.example.fininfocom;

public class ModelClass {
    public ModelClass(String emailId, String mobileNumber) {
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
    }

    private String emailId;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    private String mobileNumber;
}
