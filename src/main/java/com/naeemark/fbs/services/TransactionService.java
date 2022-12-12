package com.naeemark.fbs.services;

import com.naeemark.fbs.models.Transaction;
import com.naeemark.fbs.models.requests.TransactionRequest;
import java.util.List;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2022-12-12
 */
public interface TransactionService {
    Transaction create(TransactionRequest transactionRequest);
    List<Transaction> list();
}
