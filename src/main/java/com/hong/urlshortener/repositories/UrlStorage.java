package com.hong.urlshortener.repositories;

import java.util.ArrayList;

public class UrlStorage implements IStorage {

    ArrayList<String> arr = new ArrayList<String>();

    @Override
    public void arr(String value) {
        arr.add(value);
    }

    @Override
    public String get(int index) {
        return arr.get(index);
    }
}
