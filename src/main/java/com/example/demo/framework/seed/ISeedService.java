package com.example.demo.framework.seed;

import com.google.common.collect.ImmutableList;

import java.util.concurrent.ExecutionException;

public interface ISeedService<T> {

    boolean dataNotExists() throws ExecutionException, InterruptedException;

    ImmutableList<T> initializeData();

    String type();

    Integer order();
}
