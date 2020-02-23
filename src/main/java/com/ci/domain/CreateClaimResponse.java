package com.ci.domain;

public class CreateClaimResponse {
    private ClaimResponse claimResponse;
    private StatusType status;

    public CreateClaimResponse() {
    }

    public CreateClaimResponse(ClaimResponse claimResponse, StatusType status) {
        this.claimResponse = claimResponse;
        this.status = status;
    }

    public ClaimResponse getClaimResponse() {
        return claimResponse;
    }

    public StatusType getStatus() {
        return status;
    }
}
