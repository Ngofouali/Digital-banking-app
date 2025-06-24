package com.icodi.bankingapp.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("SA") // SA pour Saving Account
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @SuperBuilder
public class SavingAccount extends BankAccount {
    //Taux d'intérêt
    private double interestRate;
}
