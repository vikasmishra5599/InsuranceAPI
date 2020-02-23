package com.ci.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ReplacementFlightDetail {

    private String flightNo;

    @JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
