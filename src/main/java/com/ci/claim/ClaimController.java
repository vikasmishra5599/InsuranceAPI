package com.ci.claim;

import com.ci.domain.ClaimResponse;
import com.ci.domain.CreateClaimRequest;
import com.ci.domain.CreateClaimResponse;
import com.ci.domain.ErrorType;
import com.ci.domain.StatusType;
import com.ci.exception.ClaimException;
import com.ci.exception.ClaimRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ci.util.Validations.validateClaimRequest;
import static java.util.Arrays.asList;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/claims")
public class ClaimController {
    private static final Logger LOG = LoggerFactory.getLogger(ClaimController.class);

    private final ClaimService claimService;

    @Autowired
    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateClaimResponse> listClaims() {
        CreateClaimResponse claims = claimService.fetchAllClaims();
        return ResponseEntity.ok(claims);
    }

    @GetMapping(value = "/claim/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateClaimResponse> fetch(@PathVariable("id") long claimNo) {
        CreateClaimResponse claims = claimService.fetchClaimDetailForId(claimNo);
        return ResponseEntity.ok(claims);
    }

    @GetMapping(value = "/{emailId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateClaimResponse> fetchByEmailId(@PathVariable("emailId") String emailId) {
        CreateClaimResponse claims = claimService.fetchClaimDetailForEmailId(emailId);
        return ResponseEntity.ok(claims);
    }

    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateClaimResponse> postClaim(@RequestBody CreateClaimRequest claimRequest) {
        validateClaimRequest(claimRequest);
        LOG.info("Received request  to create claim with details as [{}]", claimRequest);

        CreateClaimResponse claimResponse = claimService.createClaim(claimRequest);
        return ResponseEntity.ok(claimResponse);
    }

    @ExceptionHandler(value = ClaimRequestException.class)
    public ResponseEntity<CreateClaimResponse> handleBadRequest(ClaimRequestException exception) {
        LOG.warn("Bad request was found. Errors:[{}]", exception);
        ClaimResponse claimResponse = new ClaimResponse();
        claimResponse.setErrorType(exception.getErrorTypes());
        CreateClaimResponse response = new CreateClaimResponse(claimResponse, StatusType.FAILED);
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler({ClaimException.class, Exception.class})
    public ResponseEntity<CreateClaimResponse> handleException(RuntimeException exception) {
        LOG.warn("An unexpected error occurred in claim request controller.", exception);

        ClaimResponse claimResponse = new ClaimResponse();
        claimResponse.setErrorType(asList(new ErrorType(500, "An unexpected error occurred while processing request")));

        CreateClaimResponse response = new CreateClaimResponse(claimResponse, StatusType.FAILED);
        return ResponseEntity.status(500).body(response);
    }
}

