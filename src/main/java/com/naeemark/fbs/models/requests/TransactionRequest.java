package com.naeemark.fbs.models.requests;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.naeemark.fbs.utils.Constants.ERROR_ACCOUNT_SAME;

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

    @AssertTrue(message = ERROR_ACCOUNT_SAME)
    private boolean isValid() {
        return !(fromAccountId==toAccountId);
    }
}
