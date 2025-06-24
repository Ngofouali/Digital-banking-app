package com.icodi.bankingapp.exceptions;

public class BalanceNotSufficientException extends Exception {
    public BalanceNotSufficientException(String message) {

        super(message);
    }
}
