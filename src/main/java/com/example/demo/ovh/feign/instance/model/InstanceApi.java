package com.example.demo.ovh.feign.instance.model;

import com.example.demo.framework.deserializer.DateTimeDeserializer;
import com.example.demo.ovh.feign.common.SshKeyDetailApi;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.ovh.image.feign.model.ImageApi;
import com.example.demo.ovh.feign.common.IpAddressApi;
import com.example.demo.ovh.feign.common.MonthlyBillingApi;
import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InstanceApi {

    private String id;
    private String name;
    private List<IpAddressApi> ipAddresses;
    private InstanceStatus status;
    @JsonDeserialize(using = DateTimeDeserializer.class)
    @JsonProperty("created")
    private LocalDateTime createdDate;
    private String region;
    private FlavorApi flavor;
    private ImageApi image;
    private SshKeyDetailApi sshKey;
    private MonthlyBillingApi monthlyBilling;
    private String planCode;
    private List<String> operationIds;
}
