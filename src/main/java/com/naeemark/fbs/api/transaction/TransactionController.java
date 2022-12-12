package com.naeemark.fbs.api.transaction;


import com.naeemark.fbs.api.account.AccountController;
import com.naeemark.fbs.models.Transaction;
import com.naeemark.fbs.models.requests.TransactionRequest;
import com.naeemark.fbs.models.responses.TransactionResponse;
import com.naeemark.fbs.services.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    @ApiOperation(value = "Create Transaction", notes = "Creates a new transaction", response = TransactionResponse.class, tags = {"2 - Transactions"})
    @ApiResponses(value = {
            @ApiResponse(code = 304, message = "Operation was not successful"),
            @ApiResponse(code = 417, message = "Expectations failed"),
            @ApiResponse(code = 422, message = "Request not processable")
    })
    @PostMapping
    public ResponseEntity<TransactionResponse> create(@Valid @RequestBody TransactionRequest transactionRequest) {
        logger.info(String.format("Request received for creation: %s", transactionRequest));
        Transaction transaction = transactionService.create(transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TransactionResponse(transaction));
    }

    /**
     * Gets Transactions
     *
     * @return List of Transactions
     */
    @ApiOperation(value = "List Transactions", notes = "Gets all accounts with balance", response = Collection.class, tags = {"2 - Transactions"})
    @ApiResponses(value = {
            @ApiResponse(code = 304, message = "Operation was not successful"),
            @ApiResponse(code = 417, message = "Expectations failed"),
            @ApiResponse(code = 422, message = "Request not processable")
    })
    @GetMapping
    public ResponseEntity<List<TransactionResponse>> list() {
        logger.info("Request received for Accounts List");
        List<Transaction> transactions = transactionService.list();
        List<TransactionResponse> list = transactions.stream().map(TransactionResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }
}
