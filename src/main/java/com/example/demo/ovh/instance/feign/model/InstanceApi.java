package com.example.demo.ovh.instance.feign.model;

import com.example.demo.framework.deserializer.DateTimeDeserializer;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
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

    public String getIp4Address() {

        if(ipAddresses == null) {

            return null;
        }

        return getIpAddress(4);
    }

    public String getIp6Address() {

        if(ipAddresses == null) {

            return null;
        }

        return getIpAddress(6);
    }

    private String getIpAddress(Integer version) {

        return ipAddresses.stream()
                .findFirst()
                .filter(ipAddress -> ipAddress.getVersion().equals(version))
                .map(IpAddressApi::getIp)
                .orElse(null);
    }
}
