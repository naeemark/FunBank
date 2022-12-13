package com.naeemark.fbs.repositories;

import com.naeemark.fbs.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2022-12-12
 */
@Component
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}