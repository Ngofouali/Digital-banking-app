package com.icodi.bankingapp.dto;

import lombok.Data;

@Data
public class CreditDTO {
    private String accountNumber;
    private double amount;
    private String description;
}
