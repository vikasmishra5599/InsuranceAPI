package com.ci.claim;

import com.ci.domain.ClaimResponse;
import com.ci.domain.CreateClaimRequest;
import com.ci.domain.CreateClaimResponse;
import com.ci.domain.StatusType;
import com.ci.dto.ClaimDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    private final ClaimsDetailDAO flightClaimsDetailDAO;

    @Autowired
    public ClaimService(ClaimsDetailDAO flightClaimsDetailDAO) {
        this.flightClaimsDetailDAO = flightClaimsDetailDAO;
    }

    public CreateClaimResponse createClaim(CreateClaimRequest createClaimRequest) {
        long claimOrderNo= flightClaimsDetailDAO.createClaim(createClaimRequest);
        CreateClaimResponse claimResponse = buildCreateClaimResponse(claimOrderNo);
        return claimResponse;
    }

    public CreateClaimResponse fetchAllClaims() {
        List<ClaimDetail> allClaims = flightClaimsDetailDAO.getAllClaims();
        return buildClaimSearchResponse(allClaims);
    }

    public CreateClaimResponse fetchClaimDetailForId(long id) {
        List<ClaimDetail> claimsById = flightClaimsDetailDAO.getClaimsById(id);
        return buildClaimSearchResponse(claimsById);
    }

    public CreateClaimResponse fetchClaimDetailForEmailId(String emailId) {
        List<ClaimDetail> claimsByEmailAddress = flightClaimsDetailDAO.getClaimsByEmailAddress(emailId);
        return buildClaimSearchResponse(claimsByEmailAddress);
    }

    private CreateClaimResponse buildCreateClaimResponse(long claimOrderNo) {
        ClaimResponse claimResponse = new ClaimResponse();
        claimResponse.setClaimOrderNO(claimOrderNo);
        StatusType statusType = StatusType.fromValue("Ok");

        return new CreateClaimResponse(claimResponse, statusType);
    }

    private CreateClaimResponse buildClaimSearchResponse(List<ClaimDetail> claimDetails) {
        ClaimResponse claimResponse = new ClaimResponse();
        claimResponse.setClaimDetails(claimDetails);
        StatusType statusType = StatusType.fromValue("Ok");

        return new CreateClaimResponse(claimResponse, statusType);
    }
}