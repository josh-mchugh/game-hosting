package com.example.demo.ovh.model;

import lombok.Data;

import java.util.List;

@Data
public class Instance {

    private String id;
    private String name;
    private List<IpAddress> ipAddresses;
    private String status;
    private String created;
    private String region;
    private Flavor flavor;
    private Image image;
    private String sshKey;
    private MonthlyBilling monthlyBilling;
    private String planCode;
    private List<String> operationIds;
}
