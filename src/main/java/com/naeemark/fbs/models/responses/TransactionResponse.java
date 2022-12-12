package com.naeemark.fbs.models.responses;

import com.naeemark.fbs.models.Transaction;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionResponse {

    private int id;
    private int amount;
    private int fromAccountId;
    private int toAccountId;

    public TransactionResponse(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.fromAccountId = transaction.getFromAccount().getId();
        this.toAccountId = transaction.getToAccount().getId();
    }
}
