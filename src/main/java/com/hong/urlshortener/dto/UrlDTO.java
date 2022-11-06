package com.hong.urlshortener.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UrlDTO {


    String orginUrl;
    String shortenUrl;


    int requestCount = 1;

    public UrlDTO(String origin, String shorten){
        this.orginUrl = origin+"";
        this.shortenUrl = shorten;
    }

}
