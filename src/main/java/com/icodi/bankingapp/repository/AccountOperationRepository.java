package com.icodi.bankingapp.repository;

import com.icodi.bankingapp.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountOperationRepository extends JpaRepository <AccountOperation, Long> {
}
