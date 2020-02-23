package com.ci.domain;

public class ErrorType {
   private int code;
   private String description;

    public ErrorType() {
    }

    public ErrorType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
