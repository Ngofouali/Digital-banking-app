package com.icodi.bankingapp;

import com.icodi.bankingapp.entities.*;
import com.icodi.bankingapp.model.enums.AccountStatus;
import com.icodi.bankingapp.model.enums.OperationType;
import com.icodi.bankingapp.repository.AccountOperationRepository;
import com.icodi.bankingapp.repository.BankAccountRepository;
import com.icodi.bankingapp.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication (exclude = { SecurityAutoConfiguration.class})
public class BankingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingAppApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository, AccountOperationRepository accountOperationRepository) {

        return args -> {
            // Création des clients
            Customer customerFofana = Customer.builder()
                    .name("Fofana")
                    .email("fof.ngofouali@gmail.com")
                    .phone("002250506499508")
                    .build();
            customerRepository.save(customerFofana);

            Customer customerAdama = Customer.builder()
                    .name("Adama")
                    .email("adams@gmail.com")
                    .phone("+2250655899594")
                    .build();
            customerRepository.save(customerAdama);

            Customer customerSalimata = Customer.builder()
                    .name("Salimata")
                    .email("salimata@gmail.com")
                    .phone("+2250707885699")
                    .build();
            customerRepository.save(customerSalimata);

            //Pour chaque client, on crée un compte courant et un compte épargne
            customerRepository.findAll().forEach(customer-> {
                    //Comptes courants
                    CurrentAccount currentAccount = CurrentAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .balance(Math.random()*100000)
                            .createAt(new Date())
                            .status(AccountStatus.CREATED)
                            .customer(customer)
                            .overdraft(1000)
                            .build();
            bankAccountRepository.save(currentAccount);

                    //Compte épargne
            SavingAccount savingAccount = SavingAccount.builder()
                    .id(UUID.randomUUID().toString())
                    .balance(Math.random()*100000)
                    .createAt(new Date())
                    .status(AccountStatus.CREATED)
                    .customer(customer)
                    .interestRate(3.5)
                    .build();
            bankAccountRepository.save(savingAccount);
        });

            //Pour chaque compte, on crée des opérations
            bankAccountRepository.findAll().forEach(bankAccount-> {
                for(int i =0; i < 5; i++){
                    double amount = Math.random()*100000;
                    OperationType type = Math.random() > 0.5 ? OperationType.CREDIT : OperationType.DEBIT;

                    AccountOperation operation = AccountOperation.builder()
                            .operationDate(new Date())
                            .amount(amount)
                            .operationType(type)
                            .description(type + " de " + amount)
                            .bankAccount(bankAccount)
                            .build();
                    accountOperationRepository.save(operation);
                }
            });

            System.out.println("Les données test ont été bien enregistrées !");
        };

    }
}

