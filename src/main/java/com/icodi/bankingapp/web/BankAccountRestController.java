package com.icodi.bankingapp.web;

import com.icodi.bankingapp.dto.BankAccountDTO;
import com.icodi.bankingapp.dto.CreditDTO;
import com.icodi.bankingapp.dto.DebitDTO;
import com.icodi.bankingapp.dto.TransfertDTO;
import com.icodi.bankingapp.entities.BankAccount;
import com.icodi.bankingapp.exceptions.BalanceNotSufficientException;
import com.icodi.bankingapp.exceptions.BankAccountNotFoundException;
import com.icodi.bankingapp.service.BankAccountService;
import com.icodi.bankingapp.service.BankAccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
@AllArgsConstructor
public class BankAccountRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/{accountNumber}")
    public BankAccountDTO getBankAccount(@PathVariable String accountNumber) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountNumber);
    }

    @GetMapping
    public List<BankAccountDTO> listAccounts() {
        return bankAccountService.listBankAccounts();
    }

    @PostMapping("/debit")
    public void debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.debit(debitDTO.getAccountNumber(), debitDTO.getAmount(), debitDTO.getDescription());
    }

    @PostMapping("/credit")
    public void credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        this.bankAccountService.credit(creditDTO.getAccountNumber(), creditDTO.getAmount(), creditDTO.getDescription());

    }

    @PostMapping("/transfert")
    public void transfert(@RequestBody TransfertDTO transfertDTO) throws BankAccountNotFoundException, BalanceNotSufficientException{
        this.bankAccountService.transfer(transfertDTO.getFromAccountNumber(), transfertDTO.getToAccountNumber(), transfertDTO.getAmount());

    }
}
