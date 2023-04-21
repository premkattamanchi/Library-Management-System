package com.example.Library_management.Repository;

import com.example.Library_management.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions,Integer> {

}
