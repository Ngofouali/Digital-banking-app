package com.icodi.bankingapp.entities;

import com.icodi.bankingapp.model.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name= "TYPE", length = 4)
public abstract class BankAccount {

    @Id
    private String id; //En mode UUID
    private double balance;
    private Date createAt;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne //Plusieurs comptes bancaires peuvent appartenir à un même client
    private Customer customer;

    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperations;
}
