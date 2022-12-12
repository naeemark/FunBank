package com.naeemark.fbs.services;

import com.naeemark.fbs.models.Account;
import com.naeemark.fbs.models.requests.TransactionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static com.naeemark.fbs.utils.Constants.ERROR_ACCOUNT_BALANCE;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

    private static final Logger logger = LoggerFactory.getLogger(TransactionRequest.class);

    @Autowired
    private AccountService accountService;

    @Override
    public void create(TransactionRequest transactionRequest) {
        logger.info(TransactionService.class.getName()+"::create()");
        logger.info(transactionRequest.toString());

        Account from = accountService.get(transactionRequest.getFromAccountId());
        Account to = accountService.get(transactionRequest.getToAccountId());
        if (transactionRequest.getAmount() > from.getBalance()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ERROR_ACCOUNT_BALANCE);
        }
        to.setBalance(to.getBalance()+ transactionRequest.getAmount());
        from.setBalance(from.getBalance() - transactionRequest.getAmount());

        accountService.update(to, from);
        // Save Transaction
    }
}
