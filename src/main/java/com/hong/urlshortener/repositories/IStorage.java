package com.hong.urlshortener.repositories;

public interface IStorage <T> {
    void add(T value);
    T get(int index);

    int size();

}
