package com.ci.domain;

public enum StatusType {
    OK("Ok"),
    FAILED("Failed");

    private String value;

    StatusType(String value){
        this.value = value;
    }

    public static StatusType fromValue(String type) {
        for (StatusType statusType : values()) {
            if (statusType.value.equalsIgnoreCase(type)) {
                return statusType;
            }
        }
        throw new IllegalArgumentException("Unknown status type found");
    }
}
