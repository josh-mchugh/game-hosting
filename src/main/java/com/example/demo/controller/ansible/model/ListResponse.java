package com.example.demo.controller.ansible.model;

import lombok.Data;

import java.util.List;

@Data
public class ListResponse<T> {

    private Long count;
    private Boolean next;
    private Boolean previous;
    private List<T> results;
}
