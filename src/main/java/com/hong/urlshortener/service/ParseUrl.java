package com.hong.urlshortener.service;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

@Setter @Getter
public class ParseUrl {

    String url;

    HttpServletRequest request;

    public ParseUrl(HttpServletRequest originRequest){
        this.request = originRequest;
    }

    public void pathExtract(){
        url = request.getRequestURI();
    }
    public void pathSplit() {
        url = url.substring(1);
    }




}
