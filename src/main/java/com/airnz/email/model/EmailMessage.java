package com.airnz.email.model;

import java.util.Date;
import java.util.List;

public class EmailMessage {
    private Long id;
    private String subject;
    private Date createdDateTime;
    private Date receivedDateTime;
    private Date sentDateTime;
    private String importance;
    private Boolean isRead;
    private Boolean isDraft;
    private Boolean hasAttachments;
    private String contentType;
    private String body;
    private EmailAddress sender;
    private EmailAddress from;
    private List<EmailAddress> toRecipients;
    private List<EmailAddress> ccRecipients;
    private List<EmailAddress> bccRecipients;

    public EmailMessage(Long id, String subject, Date createdDateTime, Date receivedDateTime, Date sentDateTime, String importance, Boolean isRead, Boolean isDraft, Boolean hasAttachments, String contentType, String body, EmailAddress sender, EmailAddress from, List<EmailAddress> toRecipients, List<EmailAddress> ccRecipients, List<EmailAddress> bccRecipients) {
        this.id = id;
        this.subject = subject;
        this.createdDateTime = createdDateTime;
        this.receivedDateTime = receivedDateTime;
        this.sentDateTime = sentDateTime;
        this.importance = importance;
        this.isRead = isRead;
        this.isDraft = isDraft;
        this.hasAttachments = hasAttachments;
        this.contentType = contentType;
        this.body = body;
        this.sender = sender;
        this.from = from;
        this.toRecipients = toRecipients;
        this.ccRecipients = ccRecipients;
        this.bccRecipients = bccRecipients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Date getReceivedDateTime() {
        return receivedDateTime;
    }

    public void setReceivedDateTime(Date receivedDateTime) {
        this.receivedDateTime = receivedDateTime;
    }

    public Date getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(Date sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Boolean getDraft() {
        return isDraft;
    }

    public void setDraft(Boolean draft) {
        isDraft = draft;
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
        return "EmailMessage{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", receivedDateTime=" + receivedDateTime +
                ", sentDateTime=" + sentDateTime +
                ", importance='" + importance + '\'' +
                ", isRead=" + isRead +
                ", isDraft=" + isDraft +
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
