package com.example.demo.ovh.model;

import lombok.Data;

@Data
public class IpAddress {

    private String gatewayIp;
    private String ip;
    private String networkId;
    private String type;
    private Long version;
}
