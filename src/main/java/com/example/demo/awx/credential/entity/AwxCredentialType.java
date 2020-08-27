package com.example.demo.awx.credential.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AwxCredentialType {

    MACHINE(1, "machine"),
    SOURCE_CONTROL(2, "source control");

    private final Integer id;
    private final String name;
}
