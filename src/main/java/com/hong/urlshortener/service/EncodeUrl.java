package com.hong.urlshortener.service;

import com.hong.urlshortener.utill.Base62;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


public class EncodeUrl {

    public String urlToShortenUrl(String originUrl, Base62 base62, ArrayList<String> arr){

        String index = arr.size()+"";
        byte[] Encoded = base62.encode(index.getBytes());
        String indexEncoded = new String(Encoded);
        return indexEncoded;

    }
    public String createUrl(HttpServletRequest request,String indexEncoded){
        String shortenUrl = request.getRequestURL().toString().replace(request.getRequestURI(),"/"+indexEncoded);
        return shortenUrl;
    }

}
