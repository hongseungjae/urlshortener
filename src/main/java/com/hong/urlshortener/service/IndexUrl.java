package com.hong.urlshortener.service;

import java.util.ArrayList;



public class IndexUrl {


    public boolean nullCheck(String indexDecoded){

        if(indexDecoded != null){
            return true;
        }else{
            return false;
        }

    }

    public boolean integerCheck(String indexDecoded) {

        if (indexDecoded.matches("-?\\d+")) {
            return true;
        }else{ // 런타임익셉션?
            return false;
        }

    }

    public boolean indexRangeCheck(int index, ArrayList<String> arr) {
        if ( 0 <= index && index < arr.size()) {
            return true;
        }else{
            // 런타임익셉션
            return false;
        }

    }
}
