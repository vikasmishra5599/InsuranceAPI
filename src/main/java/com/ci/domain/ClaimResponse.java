package com.ci.domain;

import com.ci.dto.ClaimDetail;

import java.util.List;

public class ClaimResponse {
    private long claimOrderNO;
    private List<ClaimDetail> claimDetails;
    private List<ErrorType> errorType;

    public long getClaimOrderNO() {
        return claimOrderNO;
    }

    public void setClaimOrderNO(long claimOrderNO) {
        this.claimOrderNO = claimOrderNO;
    }

    public List<ClaimDetail> getClaimDetails() {
        return claimDetails;
    }

    public void setClaimDetails(List<ClaimDetail> claimDetails) {
        this.claimDetails = claimDetails;
    }

    public List<ErrorType> getErrorType() {
        return errorType;
    }

    public void setErrorType(List<ErrorType> errorType) {
        this.errorType = errorType;
    }
}
