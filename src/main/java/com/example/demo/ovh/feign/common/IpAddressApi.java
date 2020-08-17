package com.example.demo.ovh.feign.common;

import lombok.Data;

@Data
public class IpAddressApi {

    private String gatewayIp;
    private String ip;
    private String networkId;
    private String type;
    private Integer version;
}
