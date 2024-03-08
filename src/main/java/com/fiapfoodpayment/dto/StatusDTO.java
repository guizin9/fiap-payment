package com.fiapfoodpayment.dto;

public class StatusDTO {
    private String statusPayment;

    public StatusDTO(String statusPayment) {
        this.statusPayment = statusPayment;
    }

    public String getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(String statusPayment) {
        this.statusPayment = statusPayment;
    }
}

