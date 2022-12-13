package com.naeemark.fbs.api.account;

import com.naeemark.fbs.repositories.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static com.naeemark.fbs.utils.Constants.DEFAULT_BALANCE;
import static org.hamcrest.Matchers.hasSize;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Tests for AccountController")
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository repository;

    @Test
    @DisplayName("POST Create - success")
    @Sql({"/schema.sql"})
    void create_Success() throws Exception {
        mockMvc.perform(post("/api/accounts"))
                // then
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.balance").exists())
                .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE));
    }

    @Test
    @DisplayName("GET - success")
    @Sql({"/schema.sql", "/data.sql"})
    void getAccount_Success() throws Exception {
        int accountId = 1;
        mockMvc.perform(get("/api/accounts/" + accountId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(accountId))
                .andExpect(jsonPath("$.balance").exists());
    }

    @Test
    @DisplayName("GET List - success")
    @Sql({"/schema.sql", "/data.sql"})
    void list_Success() throws Exception {
        mockMvc.perform(get("/api/accounts"))
                // then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].balance").exists());
    }
}