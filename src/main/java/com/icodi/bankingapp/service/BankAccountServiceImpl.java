package com.icodi.bankingapp.service;

import com.icodi.bankingapp.dto.BankAccountDTO;
import com.icodi.bankingapp.entities.AccountOperation;
import com.icodi.bankingapp.entities.BankAccount;
import com.icodi.bankingapp.entities.CurrentAccount;
import com.icodi.bankingapp.entities.SavingAccount;
import com.icodi.bankingapp.exceptions.BalanceNotSufficientException;
import com.icodi.bankingapp.exceptions.BankAccountNotFoundException;
import com.icodi.bankingapp.mappers.BankAccountMapper;
import com.icodi.bankingapp.model.enums.OperationType;
import com.icodi.bankingapp.repository.AccountOperationRepository;
import com.icodi.bankingapp.repository.BankAccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {
    private BankAccountRepository bankAccountRepository;
    private BankAccountMapper bankAccountMapper;
    private AccountOperationRepository accountOperationRepository;

    //****** Redéfinition des méthode de l'interface "BankaccountService" *******************************************
    @Override
    public BankAccountDTO getBankAccount(String accountNumber) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountNumber)
                .orElseThrow(() -> new BankAccountNotFoundException("Compte bancaire introuvable"));
        if (bankAccount instanceof SavingAccount) {
            return bankAccountMapper.fromSavingAccount((SavingAccount) bankAccount);
        } else {
            return bankAccountMapper.fromCurrentAccount((CurrentAccount) bankAccount);
        }

    }

    @Override
    public List<BankAccountDTO> listBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return bankAccounts.stream().map(account ->{
            if(account instanceof SavingAccount) {
                return bankAccountMapper.fromSavingAccount((SavingAccount) account);
            } else {
                return bankAccountMapper.fromCurrentAccount((CurrentAccount) account);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public void debit(String accountNumber, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount = bankAccountRepository.findById(accountNumber)
                .orElseThrow(() -> new BankAccountNotFoundException("Compte bancaire introuvable"));
        if (bankAccount.getBalance() < amount) {
           throw new BalanceNotSufficientException("Solde insuffisant");
        }

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setOperationType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() - amount);

    }

    @Override
    public void credit(String accountNumber, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(accountNumber).orElseThrow(() -> new BankAccountNotFoundException("Compte bancaire introuvable"));
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setOperationType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        debit(fromAccountNumber, amount, "Transfert - DEBIT à " + toAccountNumber);
        credit(fromAccountNumber, amount, "Transfert - CREDIT à " + fromAccountNumber);
    }
}
