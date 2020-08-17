package com.example.demo.ovh.feign.model;

import lombok.Data;

@Data
public class OvhMonthlyBillingApi {

    private String since;
    private String status;
}
