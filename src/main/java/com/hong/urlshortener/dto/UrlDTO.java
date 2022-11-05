package com.hong.urlshortener.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UrlDTO {
    String url;
    int requestCount = 0;

    UrlDTO(String url){
        this.url = url;
    }

}
