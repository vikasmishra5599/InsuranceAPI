package com.ci.util;

import com.ci.domain.CreateClaimRequest;
import com.ci.domain.ErrorType;
import com.ci.exception.ClaimRequestException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ci.domain.DelayedConsequences.CANCELLATION;
import static com.ci.domain.DelayedConsequences.DELAY;
import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.springframework.util.CollectionUtils.isEmpty;

public class Validations {

    public static void validateClaimRequest(CreateClaimRequest request) {
        List<ErrorType> errorTypes = new ArrayList<>();

        if (isBlank(request.getFirstName())) {
            errorTypes.add(new ErrorType(400, "FIRST_NAME_MISSING"));
        }

        if (isBlank(request.getLastName())) {
            errorTypes.add(new ErrorType(400, "LAST_NAME_MISSING"));
        }

        if (isBlank(request.getEmailId()) || isValidEmailAddress(request.getEmailId())) {
            errorTypes.add(new ErrorType(400, "INVALID_EMAIL_ID"));
        }

        if (isBlank(request.getPolicyNumber())) {
            errorTypes.add(new ErrorType(400, "POLICY_MISSING"));
        }
        if (isBlank(request.getOriginalFlightNumber())) {
            errorTypes.add(new ErrorType(400, "ORIGINAL_FLIGHT_NUMBER_MISSING"));
        }

        if (isNull(request.getDateOfOriginalFlight()) || isNotWithinAllowedDate(request.getDateOfOriginalFlight())) {
            errorTypes.add(new ErrorType(400, "DATE_OF_ORIGINAL_FLIGHT_SHOULD_BE_WITHIN_45_DAYS"));
        }

        if (CANCELLATION.equals(request.getConsequenceOfDelay()) &&
                (isNull(request.getReplacementFlightDetail()) ||
                        isBlank(request.getReplacementFlightDetail().getFlightNo()) ||
                        isNull(request.getReplacementFlightDetail().getDate()))
        ) {
            errorTypes.add(new ErrorType(400, "INVALID_CANCELLATION_DETAIL"));
        }

        if (DELAY.equals(request.getConsequenceOfDelay()) && isBlank(request.getDelayTime())) {
            errorTypes.add(new ErrorType(400, "DELAY_TIME_MISSING"));
        }

        if (!isEmpty(errorTypes)) {
            throw new ClaimRequestException(errorTypes);
        }
    }

    private static boolean isNotWithinAllowedDate(Date date) {
        LocalDate validDateForClaim = LocalDate.now().minusDays(45);
        LocalDate originalFlyingDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return originalFlyingDate.isBefore(validDateForClaim);
    }

    private static boolean isValidEmailAddress(String emailAddress) {
        Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher regMatcher = regexPattern.matcher(emailAddress);
        return !regMatcher.matches();
    }

}
