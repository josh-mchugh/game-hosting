package com.example.demo.framework.web;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Collection;

@Value
@AllArgsConstructor
public class Select2Response<T> {

    Collection<T> results;
}
