package com.naeemark.fbs.models.requests;

import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2022-12-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionRequest {

    @Positive
    private int amount;

    @Positive
    private int fromAccountId;

    @Positive
    private int toAccountId;
}
