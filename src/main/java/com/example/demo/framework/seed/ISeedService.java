package com.example.demo.framework.seed;

import com.google.common.collect.ImmutableList;

public interface ISeedService<T> {

    boolean dataNotExists();

    ImmutableList<T> initializeData();

    String type();

    Integer order();
}
