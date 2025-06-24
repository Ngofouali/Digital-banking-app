package com.icodi.bankingapp.service;

import com.icodi.bankingapp.dto.BankAccountDTO;
import com.icodi.bankingapp.exceptions.BalanceNotSufficientException;
import com.icodi.bankingapp.exceptions.BankAccountNotFoundException;

import java.util.List;

public interface BankAccountService {
    BankAccountDTO getBankAccount(String accountNumber) throws BankAccountNotFoundException;
    List<BankAccountDTO> listBankAccounts();
    void debit(String accountNumber, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountNumber, double amount, String description) throws BankAccountNotFoundException;
    void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;
}
