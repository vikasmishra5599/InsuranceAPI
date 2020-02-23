package com.ci.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CreateClaimRequest {

    private String firstName;
    private String lastName;
    private String emailId;
    private String policyNumber;
    private String originalFlightNumber;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date dateOfOriginalFlight;
    private DelayedReasonTypes reasonOfDelay;
    private DelayedConsequences consequenceOfDelay;
    private ReplacementFlightDetail replacementFlightDetail;

    private String delayTime;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getOriginalFlightNumber() {
        return originalFlightNumber;
    }

    public void setOriginalFlightNumber(String originalFlightNumber) {
        this.originalFlightNumber = originalFlightNumber;
    }

    public Date getDateOfOriginalFlight() {
        return dateOfOriginalFlight;
    }

    public void setDateOfOriginalFlight(Date dateOfOriginalFlight) {
        this.dateOfOriginalFlight = dateOfOriginalFlight;
    }

    public ReplacementFlightDetail getReplacementFlightDetail() {
        return replacementFlightDetail;
    }

    public void setReplacementFlightDetail(ReplacementFlightDetail replacementFlightDetail) {
        this.replacementFlightDetail = replacementFlightDetail;
    }

    public String getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(String delayTime) {
        this.delayTime = delayTime;
    }

    public DelayedReasonTypes getReasonOfDelay() {
        return reasonOfDelay;
    }

    public void setReasonOfDelay(DelayedReasonTypes reasonOfDelay) {
        this.reasonOfDelay = reasonOfDelay;
    }

    public DelayedConsequences getConsequenceOfDelay() {
        return consequenceOfDelay;
    }

    public void setConsequenceOfDelay(DelayedConsequences consequenceOfDelay) {
        this.consequenceOfDelay = consequenceOfDelay;
    }
}

