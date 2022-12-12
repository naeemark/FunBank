package com.naeemark.fbs.services;

import com.naeemark.fbs.models.Account;
import com.naeemark.fbs.repositories.AccountRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static com.naeemark.fbs.utils.Constants.DEFAULT_BALANCE;
import static com.naeemark.fbs.utils.Constants.ERROR_ACCOUNT_NOT_FOUND;
import static com.naeemark.fbs.utils.Constants.ERROR_ACCOUNT_SERVICE;
import static com.naeemark.fbs.utils.Constants.ERROR_DUPLICATE_KEY_ATTRIBUTE;


/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2022-12-11
 */

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    AccountRepository accountRepository;
    @Override
    public Account create() {
        try {
            Account account = new Account(DEFAULT_BALANCE);
            return accountRepository.save(account);
        } catch (DataIntegrityViolationException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, ERROR_DUPLICATE_KEY_ATTRIBUTE);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ERROR_ACCOUNT_SERVICE);
        }
    }

    @Override
    public Account get(int accountId) {
        Optional<Account> getById = accountRepository.findById(accountId);
        if (getById.isPresent()) return getById.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, ERROR_ACCOUNT_NOT_FOUND);
    }

    @Override
    public List<Account> list() {
        return accountRepository.findAll();
    }

    @Override
    public void update(Account to, Account from) {
        accountRepository.saveAll(Arrays.asList(to, from));
    }
}
