package com.naeemark.fbs.api.transaction;

import com.naeemark.fbs.api.account.AccountController;
import com.naeemark.fbs.models.Account;
import com.naeemark.fbs.models.Transaction;
import com.naeemark.fbs.models.requests.TransactionRequest;
import com.naeemark.fbs.models.responses.TransactionResponse;
import com.naeemark.fbs.repositories.AccountRepository;
import com.naeemark.fbs.repositories.TransactionRepository;
import com.naeemark.fbs.services.AccountService;
import com.naeemark.fbs.services.TransactionService;
import com.naeemark.fbs.utils.TestDataFactory;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2022-12-12
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
@DisplayName("Unit tests for TransactionController")
class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TransactionService service;

    @MockBean
    private TransactionRepository repository;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    @DisplayName("POST Create - success")
    void create_Success() throws Exception {
        TransactionRequest request = new TransactionRequest(1, 1, 2);
        Transaction transaction = Transaction.builder().id(1).amount(1).fromAccount(new Account(1, 1)).toAccount(new Account(2, 1)).build();

        when(service.create(request)).thenReturn(transaction);

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestDataFactory.asJsonString(request))
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(transaction.getId()))
                .andExpect(jsonPath("$.amount").exists())
                .andExpect(jsonPath("$.amount").value(transaction.getAmount()))
                .andExpect(jsonPath("$.fromAccountId").exists())
                .andExpect(jsonPath("$.fromAccountId").value(transaction.getFromAccount().getId()))
                .andExpect(jsonPath("$.toAccountId").exists())
                .andExpect(jsonPath("$.toAccountId").value(transaction.getToAccount().getId()));
    }


    @Test
    @DisplayName("GET List - success")
    void list_Success() throws Exception {
        Transaction transaction = Transaction.builder().id(1).amount(1).fromAccount(new Account(1, 1)).toAccount(new Account(2, 1)).build();
        //given
        when(service.list()).thenReturn(Collections.singletonList(transaction));
        // when
        mockMvc.perform(get("/api/transactions"))
                // then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").exists())
                .andExpect(jsonPath("$.[0].id").value(transaction.getId()))
                .andExpect(jsonPath("$.[0].amount").exists())
                .andExpect(jsonPath("$.[0].amount").value(transaction.getAmount()))
                .andExpect(jsonPath("$.[0].fromAccountId").exists())
                .andExpect(jsonPath("$.[0].fromAccountId").value(transaction.getFromAccount().getId()))
                .andExpect(jsonPath("$.[0].toAccountId").exists())
                .andExpect(jsonPath("$.[0].toAccountId").value(transaction.getToAccount().getId()));
    }
}