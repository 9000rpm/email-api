package com.airnz.email.model;

import java.util.Date;
import java.util.List;

public class EmailMessageRequest {
    private String subject;
    private String importance;
    private Boolean hasAttachments;
    private String contentType;
    private String body;
    private EmailAddress sender;
    private EmailAddress from;
    private List<EmailAddress> toRecipients;
    private List<EmailAddress> ccRecipients;
    private List<EmailAddress> bccRecipients;

    public EmailMessageRequest(String subject, String importance, Boolean hasAttachments, String contentType, String body, EmailAddress sender, EmailAddress from, List<EmailAddress> toRecipients, List<EmailAddress> ccRecipients, List<EmailAddress> bccRecipients) {
        this.subject = subject;
        this.importance = importance;
        this.hasAttachments = hasAttachments;
        this.contentType = contentType;
        this.body = body;
        this.sender = sender;
        this.from = from;
        this.toRecipients = toRecipients;
        this.ccRecipients = ccRecipients;
        this.bccRecipients = bccRecipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public Boolean getHasAttachments() {
        return hasAttachments;
    }

    public void setHasAttachments(Boolean hasAttachments) {
        this.hasAttachments = hasAttachments;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public EmailAddress getSender() {
        return sender;
    }

    public void setSender(EmailAddress sender) {
        this.sender = sender;
    }

    public EmailAddress getFrom() {
        return from;
    }

    public void setFrom(EmailAddress from) {
        this.from = from;
    }

    public List<EmailAddress> getToRecipients() {
        return toRecipients;
    }

    public void setToRecipients(List<EmailAddress> toRecipients) {
        this.toRecipients = toRecipients;
    }

    public List<EmailAddress> getCcRecipients() {
        return ccRecipients;
    }

    public void setCcRecipients(List<EmailAddress> ccRecipients) {
        this.ccRecipients = ccRecipients;
    }

    public List<EmailAddress> getBccRecipients() {
        return bccRecipients;
    }

    public void setBccRecipients(List<EmailAddress> bccRecipients) {
        this.bccRecipients = bccRecipients;
    }

    @Override
    public String toString() {
        return "EmailMessageRequest{" +
                "subject='" + subject + '\'' +
                ", importance='" + importance + '\'' +
                ", hasAttachments=" + hasAttachments +
                ", contentType='" + contentType + '\'' +
                ", body='" + body + '\'' +
                ", sender=" + sender +
                ", from=" + from +
                ", toRecipients=" + toRecipients +
                ", ccRecipients=" + ccRecipients +
                ", bccRecipients=" + bccRecipients +
                '}';
    }
}
