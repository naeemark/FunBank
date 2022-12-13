package com.naeemark.fbs.services;

import com.naeemark.fbs.models.Account;
import com.naeemark.fbs.models.Transaction;
import com.naeemark.fbs.models.requests.TransactionRequest;
import com.naeemark.fbs.repositories.AccountRepository;
import com.naeemark.fbs.repositories.TransactionRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static com.naeemark.fbs.utils.Constants.ERROR_ACCOUNT_BALANCE;
import static com.naeemark.fbs.utils.Constants.ERROR_ACCOUNT_SERVICE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class TransactionServiceTest {


    @Autowired
    private TransactionService service;
    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionRepository repository;

    final Transaction transaction = Transaction.builder().id(1).amount(1).fromAccount(new Account(1, 1)).toAccount(new Account(2, 1)).build();

    @BeforeEach
    void mockCache() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Create - Success")
    void create_Success() {
        TransactionRequest request = new TransactionRequest(1, 1, 2);
        when(accountService.get(anyInt())).thenReturn(transaction.getFromAccount(),transaction.getToAccount());
        when(repository.save(any())).thenReturn(transaction);

        Transaction result = service.create(request);
        assertNotNull(result);
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("Create - Exception")
    void create_Exception() {
        TransactionRequest request = new TransactionRequest(100, 1, 2);
        when(accountService.get(anyInt())).thenReturn(transaction.getFromAccount(),transaction.getToAccount());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> service.create(request));
        assertNotNull(exception);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, exception.getStatus());
        assertEquals(ERROR_ACCOUNT_BALANCE, exception.getReason());
        verify(repository, times(0)).save(any());
    }


    @Test
    @DisplayName("List - Success")
    void list() {
        when(repository.findAll()).thenReturn(Collections.singletonList(transaction));

        List<Transaction> list = service.list();
        assertNotNull(list);
        assertEquals(list.get(0).getId(), transaction.getId());
        verify(repository, times(1)).findAll();
    }
}