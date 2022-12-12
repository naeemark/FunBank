package com.naeemark.fbs.services;

import com.naeemark.fbs.models.Account;
import com.naeemark.fbs.models.Transaction;
import com.naeemark.fbs.models.requests.TransactionRequest;
import com.naeemark.fbs.repositories.TransactionRepository;
import java.util.List;
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

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction create(TransactionRequest transactionRequest) {
        logger.info(TransactionService.class.getName()+"::create()");
        logger.info(transactionRequest.toString());

        int amount = transactionRequest.getAmount();
        Account fromAccount = accountService.get(transactionRequest.getFromAccountId());
        Account toAccount = accountService.get(transactionRequest.getToAccountId());
        if (amount > fromAccount.getBalance()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ERROR_ACCOUNT_BALANCE);
        }
        toAccount.setBalance(toAccount.getBalance()+ amount);
        fromAccount.setBalance(fromAccount.getBalance() - amount);

        // Update accounts
        accountService.update(toAccount, fromAccount);
        
        // Save Transaction
        Transaction transaction = Transaction.builder()
                .amount(amount)
                .fromAccount(fromAccount)
                .toAccount(toAccount)
                .build();
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
