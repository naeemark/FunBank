package com.naeemark.fbs.services;

import com.naeemark.fbs.models.requests.TransactionRequest;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2022-12-12
 */
public interface TransactionService {
    void create(TransactionRequest transactionRequest);
}
