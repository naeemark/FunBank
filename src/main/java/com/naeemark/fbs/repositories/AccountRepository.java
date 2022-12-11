package com.naeemark.fbs.repositories;

import com.naeemark.fbs.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2022-12-11
 */
@Component
public interface AccountRepository extends JpaRepository<Account, Integer> {
}