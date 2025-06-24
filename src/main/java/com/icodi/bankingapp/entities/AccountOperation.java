package com.icodi.bankingapp.entities;

import com.icodi.bankingapp.model.enums.OperationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountOperation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private double amount;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    private String description;

    @ManyToOne
    private BankAccount bankAccount;
}
