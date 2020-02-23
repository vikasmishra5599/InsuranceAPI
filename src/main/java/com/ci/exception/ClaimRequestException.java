package com.ci.exception;

import com.ci.domain.ErrorType;

import java.util.List;

public class ClaimRequestException extends RuntimeException {

    private List<ErrorType> errorTypes;

    public ClaimRequestException(List<ErrorType> errorTypes) {
        this.errorTypes = errorTypes;
    }

    public List<ErrorType> getErrorTypes() {
        return errorTypes;
    }
}
