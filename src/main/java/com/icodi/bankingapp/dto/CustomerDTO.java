package com.icodi.bankingapp.dto;

import com.icodi.bankingapp.entities.BankAccount;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;

}
