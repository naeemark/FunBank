package com.naeemark.fbs.api.account;

import com.naeemark.fbs.models.Account;
import com.naeemark.fbs.models.responses.AccountResponse;
import com.naeemark.fbs.services.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2022-12-11
 */

@Api(tags = "1 - Accounts", description = "Operations related to Accounts")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    /**
     * Creates a new account with default balance
     *
     * @return Account Response
     */
    @ApiOperation(value = "Create Account", notes = "Creates a new account with default balance = 100", response = AccountResponse.class, tags = {"1 - Accounts"})
    @ApiResponses(value = {
            @ApiResponse(code = 304, message = "Operation was not successful"),
            @ApiResponse(code = 417, message = "Expectations failed"),
            @ApiResponse(code = 422, message = "Request not processable")
    })
    @PostMapping
    public ResponseEntity<Account> create() {
        logger.info("Request received for creation");
        Account account = accountService.create();
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    /**
     * Gets Account information
     *
     * @return Account Response
     */
    @ApiOperation(value = "Get Account by ID", notes = "Gets account with balance", response = AccountResponse.class, tags = {"1 - Accounts"})
    @ApiResponses(value = {
            @ApiResponse(code = 304, message = "Operation was not successful"),
            @ApiResponse(code = 400, message = "Validation Error"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 417, message = "Expectations failed"),
            @ApiResponse(code = 422, message = "Request not processable")
    })
    @GetMapping(value = "/{accountId}")
    public ResponseEntity<Account> get(@Valid @PathVariable int accountId) {
        logger.info("Request received to get Account");
        Account account = accountService.get(accountId);
        return ResponseEntity.ok(account);
    }

    /**
     * Gets Accounts List
     *
     * @return Account Response
     */
    @ApiOperation(value = "List Accounts", notes = "Gets all accounts with balance", response = AccountResponse.class, tags = {"1 - Accounts"})
    @ApiResponses(value = {
            @ApiResponse(code = 304, message = "Operation was not successful"),
            @ApiResponse(code = 417, message = "Expectations failed"),
            @ApiResponse(code = 422, message = "Request not processable")
    })
    @GetMapping
    public ResponseEntity<List<AccountResponse>> list() {
        logger.info("Request received for Accounts List");
        List<Account> accounts = accountService.list();
        List<AccountResponse> list = accounts.stream().map(AccountResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
