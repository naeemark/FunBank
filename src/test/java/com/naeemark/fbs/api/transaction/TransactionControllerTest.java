package com.naeemark.fbs.api.transaction;

import com.naeemark.fbs.models.Account;
import com.naeemark.fbs.models.Transaction;
import com.naeemark.fbs.models.requests.TransactionRequest;
import com.naeemark.fbs.utils.TestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItems;
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
@DisplayName("tests for TransactionController")
class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST Create - success")
    @Sql({"/schema.sql", "/data.sql"})
    void create_Success() throws Exception {
        TransactionRequest request = new TransactionRequest(1, 1, 2);

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestDataFactory.asJsonString(request))
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.amount").exists())
                .andExpect(jsonPath("$.fromAccountId").exists())
                .andExpect(jsonPath("$.toAccountId").exists());
    }

    @Test
    @DisplayName("GET List - success")
    @Sql({"/schema.sql", "/data.sql"})
    void list_Success() throws Exception {
        mockMvc.perform(get("/api/transactions"))
                // then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[*].id").exists())
                .andExpect(jsonPath("$.[*].amount").exists())
                .andExpect(jsonPath("$.[*].fromAccountId").exists())
                .andExpect(jsonPath("$.[*].toAccountId").exists());
    }
}