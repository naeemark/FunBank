package com.naeemark.fbs.api.account;

import com.naeemark.fbs.api.account.AccountController;
import com.naeemark.fbs.models.Account;
import com.naeemark.fbs.repositories.AccountRepository;
import com.naeemark.fbs.services.AccountService;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.naeemark.fbs.utils.Constants.DEFAULT_BALANCE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2022-12-12
 */

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(AccountController.class)
@DisplayName("Unit tests for AccountController")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@Transactional
@ActiveProfiles("test")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

//    @MockBean
//    private AccountService service;

    @Autowired
    private AccountRepository repository;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    @Sql({ "/schema.sql"})
    void setup(){
        repository.deleteAll();
    }

    @Test
//    @Sql({ "/schema.sql"})
    @DisplayName("POST Create - success")
    void create_Success() throws Exception {
//        Account account = new Account(1, 100);
        //given
//        when(service.create()).thenReturn(account);
        // when
        mockMvc.perform(post("/api/accounts"))
                // then
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.balance").exists())
                .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE));
    }
//
//    @Test
//    @DisplayName("GET - success")
//    void getAccount_Success() throws Exception {
//        Account account = new Account(1, 100);
//        //given
//        when(service.get(account.getId())).thenReturn(account);
//        // when
//        mockMvc.perform(get("/api/accounts/"+account.getId()))
//                // then
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").exists())
//                .andExpect(jsonPath("$.id").value(account.getId()))
//                .andExpect(jsonPath("$.balance").exists())
//                .andExpect(jsonPath("$.balance").value(account.getBalance()));
//    }
//
//    @Test
//    @DisplayName("GET List - success")
//    void list_Success() throws Exception {
//        Account account = new Account(1, 100);
//        //given
//        when(service.list()).thenReturn(Collections.singletonList(account));
//        // when
//        mockMvc.perform(get("/api/accounts"))
//                // then
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.[0].id").exists())
//                .andExpect(jsonPath("$.[0].id").value(account.getId()))
//                .andExpect(jsonPath("$.[0].balance").exists())
//                .andExpect(jsonPath("$.[0].balance").value(account.getBalance()));
//    }
}