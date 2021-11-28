package com.example.demo.framework.seed;

import com.google.common.collect.ImmutableList;

import java.util.concurrent.ExecutionException;

public interface SeedService<T> {

    boolean dataNotExists() throws ExecutionException, InterruptedException;

    ImmutableList<T> initializeData() throws ExecutionException, InterruptedException;

    String type();

    Integer order();
}
