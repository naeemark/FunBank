package com.naeemark.fbs.services;

import com.naeemark.fbs.models.Account;
import java.util.List;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2022-12-11
 */
public interface AccountService {

    Account create();
    Account get(int accountId);
    List<Account> list();
}
