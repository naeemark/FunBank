package com.naeemark.fbs.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Naeem <naeemark@gmail.com>.
 * <p>
 * Created on: 2022-12-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HealthResponse {

    private String serviceName;
    private String status;
}
