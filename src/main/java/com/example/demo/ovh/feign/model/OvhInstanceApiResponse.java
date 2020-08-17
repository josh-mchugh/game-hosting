package com.example.demo.ovh.feign.model;

import com.example.demo.framework.deserializer.DateTimeDeserializer;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OvhInstanceApiResponse {

    private String id;
    private String name;
    private List<OvhIpAddressApi> ipAddresses;
    private InstanceStatus status;
    @JsonDeserialize(using = DateTimeDeserializer.class)
    @JsonProperty("created")
    private LocalDateTime createdDate;
    private String region;
    private OvhFlavorApiResponse flavor;
    private OvhImageApiResponse image;
    private OvhSshKeyDetail sshKey;
    private OvhMonthlyBillingApi monthlyBilling;
    private String planCode;
    private List<String> operationIds;
}
