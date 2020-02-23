package com.ci.domain;

public enum DelayedReasonTypes {
    Weather("WEATHER"),
    Technical("TECHNICAL"),
    Mechanical("MECHANICAL"),
    Staffing("STAFFING"),
    Security("SECURITY"),
    Other("OTHER");

    private String value;

    DelayedReasonTypes(String value) {
        this.value = value;
    }

    public static DelayedReasonTypes fromValue(String reason) {
        for (DelayedReasonTypes reasonType : values()) {
            if (reasonType.value.equalsIgnoreCase(reason)) {
                return reasonType;
            }
        }
        throw new IllegalArgumentException("Unknown delayed reason type found.");
    }
}