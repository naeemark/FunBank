package com.naeemark.fbs.api.transaction;


import com.naeemark.fbs.api.account.AccountController;
import com.naeemark.fbs.models.Account;
import com.naeemark.fbs.models.requests.TransactionRequest;
import com.naeemark.fbs.models.responses.AccountResponse;
import com.naeemark.fbs.services.AccountService;
import com.naeemark.fbs.services.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2022-12-12
 */

@Api(tags = "2 - Transactions", description = "Operations related to Transactions")
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    /**
     * Creates a new account with default balance
     *
     * @return Account Response
     */
    @ApiOperation(value = "Create Transaction", notes = "Creates a new transaction", response = AccountResponse.class, tags = {"2 - Transactions"})
    @ApiResponses(value = {
            @ApiResponse(code = 304, message = "Operation was not successful"),
            @ApiResponse(code = 417, message = "Expectations failed"),
            @ApiResponse(code = 422, message = "Request not processable")
    })
    @PostMapping
    public ResponseEntity<Account> create(@Valid @RequestBody TransactionRequest transactionRequest) {
        logger.info(String.format("Request received for creation: %s", transactionRequest));
        transactionService.create(transactionRequest);
        return ResponseEntity.ok().build();
    }
}
