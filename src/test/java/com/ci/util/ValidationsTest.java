package com.ci.util;

import com.ci.domain.CreateClaimRequest;
import com.ci.exception.ClaimRequestException;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static com.ci.domain.DelayedConsequences.DELAY;
import static com.ci.domain.DelayedReasonTypes.Security;
import static com.ci.util.Validations.validateClaimRequest;

public class ValidationsTest {

    @Test
    public void verify_mandatory_field_not_blank() {
        CreateClaimRequest claimRequest = createClaimRequest();
        validateClaimRequest(claimRequest);
    }

    @Test(expected = ClaimRequestException.class)
    public void throw_error_when_mandatory_field_blank() {
        CreateClaimRequest claimRequest = createClaimRequest();
        claimRequest.setFirstName("");
        validateClaimRequest(claimRequest);
    }

    @Test(expected = ClaimRequestException.class)
    public void throw_error_when_invalid_email_id() {
        CreateClaimRequest claimRequest = createClaimRequest();
        claimRequest.setEmailId("abcdd@qwqwq");
        validateClaimRequest(claimRequest);
    }

    @Test(expected = ClaimRequestException.class)
    public void throw_error_when_original_date_is_older_than_45_days() {
        Date d = Date.from(LocalDate.now().minusDays(46).atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        CreateClaimRequest claimRequest = createClaimRequest();
        claimRequest.setDateOfOriginalFlight(d);
        validateClaimRequest(claimRequest);
    }

    private CreateClaimRequest createClaimRequest() {
        CreateClaimRequest claimRequest = new CreateClaimRequest();
        claimRequest.setFirstName("FNAME");
        claimRequest.setLastName("LNAME");
        claimRequest.setEmailId("emailId@gmail.com");
        claimRequest.setPolicyNumber("POLICYNO");
        claimRequest.setConsequenceOfDelay(DELAY);
        claimRequest.setOriginalFlightNumber("123");
        claimRequest.setDateOfOriginalFlight(new Date());
        claimRequest.setReasonOfDelay(Security);
        claimRequest.setDelayTime("12");
        return claimRequest;
    }
}