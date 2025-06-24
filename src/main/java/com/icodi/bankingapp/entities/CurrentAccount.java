package com.icodi.bankingapp.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("CA") //CA pour Current Account
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class CurrentAccount extends BankAccount {
    //Découvert on
    private double overdraft;


}
