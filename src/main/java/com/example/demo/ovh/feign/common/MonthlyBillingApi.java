package com.example.demo.ovh.feign.common;

import lombok.Data;

@Data
public class MonthlyBillingApi {

    private String since;
    private String status;
}
