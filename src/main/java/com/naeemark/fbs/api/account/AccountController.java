package com.naeemark.fbs.api.account;

import com.naeemark.fbs.models.Account;
import com.naeemark.fbs.models.responses.AccountResponse;
import com.naeemark.fbs.services.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2022-12-11
 */

@Api(tags = "1 - Account", description = "Operations related to User Registration")
@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    /**
     * Creates a new account with default balance
     *
     * @return Account Response
     */
    @ApiOperation(value = "Create Account", notes = "Creates a new account with default balance = 100", response = AccountResponse.class, tags = {"1 - Account"})
    @ApiResponses(value = {
            @ApiResponse(code = 304, message = "Operation was not successful"),
            @ApiResponse(code = 417, message = "Expectations failed"),
            @ApiResponse(code = 422, message = "Request not processable")
    })
    @PostMapping(value = "/account")
    public AccountResponse signUp() {

        logger.info("Request received");

        Account account = accountService.create();
        return new AccountResponse(account.getId(), account.getBalance());
    }
}
