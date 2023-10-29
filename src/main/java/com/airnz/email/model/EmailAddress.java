package com.airnz.email.model;

public class EmailAddress {

    private String emailAddress;
    private String name;

    public EmailAddress(String emailAddress, String name) {
        this.emailAddress = emailAddress;
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmailAddress{" +
                "emailAddress='" + emailAddress + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
