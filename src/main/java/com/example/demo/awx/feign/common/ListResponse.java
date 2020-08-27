package com.example.demo.awx.feign.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListResponse<T> {

    private Long count = 0L;
    private Boolean next = false;
    private Boolean previous = false;
    private List<T> results = new ArrayList<>();
}
