package com.ci.dto;

import java.math.BigInteger;
import java.util.Date;

public class ClaimDetail {
    private BigInteger id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String policyNumber;
    private String originalFlightNumber;
    private Date dateOfOriginalFlight;
    private String reasonOfDelay;
    private String consequenceOfDelay;
    private String replacementFlightNo;
    private Date replacementFlightDate;
    private String delayTime;

    private ClaimDetail() {
    }

    public BigInteger getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getOriginalFlightNumber() {
        return originalFlightNumber;
    }

    public Date getDateOfOriginalFlight() {
        return dateOfOriginalFlight;
    }

    public String getReasonOfDelay() {
        return reasonOfDelay;
    }

    public String getConsequenceOfDelay() {
        return consequenceOfDelay;
    }

    public String getReplacementFlightNo() {
        return replacementFlightNo;
    }

    public Date getReplacementFlightDate() {
        return replacementFlightDate;
    }

    public String getDelayTime() {
        return delayTime;
    }

    public static ClaimDetailBuilder builder() {
        return new ClaimDetail().new ClaimDetailBuilder();
    }

    public class ClaimDetailBuilder {

        public ClaimDetail build() {
            return ClaimDetail.this;
        }

        public ClaimDetailBuilder withId(BigInteger id) {
            ClaimDetail.this.id = id;
            return this;
        }

        public ClaimDetailBuilder withFirstName(String firstName) {
            ClaimDetail.this.firstName = firstName;
            return this;
        }

        public ClaimDetailBuilder withLastName(String lastName) {
            ClaimDetail.this.lastName = lastName;
            return this;
        }

        public ClaimDetailBuilder withEmailId(String emailId) {
            ClaimDetail.this.emailId = emailId;
            return this;
        }

        public ClaimDetailBuilder withPolicyNumber(String policyNumber) {
            ClaimDetail.this.policyNumber = policyNumber;
            return this;
        }

        public ClaimDetailBuilder withOriginalFlightNumber(String originalFlightNumber) {
            ClaimDetail.this.originalFlightNumber = originalFlightNumber;
            return this;
        }

        public ClaimDetailBuilder withReasonOfDelay(String reasonOfDelay) {
            ClaimDetail.this.reasonOfDelay = reasonOfDelay;
            return this;
        }

        public ClaimDetailBuilder withConsequenceOfDelay(String consequenceOfDelay) {
            ClaimDetail.this.consequenceOfDelay = consequenceOfDelay;
            return this;
        }

        public ClaimDetailBuilder withDelayTime(String delayTime) {
            ClaimDetail.this.delayTime = delayTime;
            return this;
        }

        public ClaimDetailBuilder withOriginalFlightDate(Date dateOfOriginalFlight) {
            ClaimDetail.this.dateOfOriginalFlight = dateOfOriginalFlight;
            return this;
        }

        public ClaimDetailBuilder withReplacementFlightDate(Date replacementFlightDate) {
            ClaimDetail.this.replacementFlightDate = replacementFlightDate;
            return this;
        }

        public ClaimDetailBuilder withReplacementFlightNo(String replacementFlightNo) {
            ClaimDetail.this.replacementFlightNo = replacementFlightNo;
            return this;
        }
    }
}