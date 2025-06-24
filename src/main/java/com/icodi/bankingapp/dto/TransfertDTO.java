package com.icodi.bankingapp.dto;

import lombok.Data;

@Data
public class TransfertDTO {
    private String fromAccountNumber;
    private String toAccountNumber;
    private double amount;
}
