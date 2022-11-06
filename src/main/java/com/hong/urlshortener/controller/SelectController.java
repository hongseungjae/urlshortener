package com.hong.urlshortener.controller;

import com.hong.urlshortener.dto.UrlDTO;
import com.hong.urlshortener.repositories.IStorage;
import com.hong.urlshortener.repositories.ListStorage;
import com.hong.urlshortener.service.DecodeUrl;
import com.hong.urlshortener.service.IndexUrl;
import com.hong.urlshortener.service.ParseUrl;
import com.hong.urlshortener.utill.Base62;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URI;

import static com.hong.urlshortener.controller.IndexController.base62;
import static com.hong.urlshortener.controller.IndexController.listStorage;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class SelectController {


    // static IStorage iStorage = new ListStorage();

    @RequestMapping(value = "/*/count", method = GET)
    public UrlDTO count(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("count");

        ParseUrl parseUrl = new ParseUrl(request);
        parseUrl.pathExtract();  // http://localhost:8080/abcd/ -> /abcd/count
        parseUrl.pathCountSplit();    // /abcd/count -> abcd

        DecodeUrl decodeUrl = new DecodeUrl();
        String indexDecoded = decodeUrl.urlToIndex(parseUrl.getUrl(),base62); // abcd -> 58

        IndexUrl indexUrl = new IndexUrl();

        if(indexUrl.integerCheck(indexDecoded) && indexUrl.nullCheck(indexDecoded)){ //널,숫자 체크
            int index = Integer.parseInt(indexDecoded);
            if(indexUrl.indexRangeCheck(index, listStorage)){  // arr 범위내에 인덱스
                UrlDTO urlDto = listStorage.get(index);
                int count = urlDto.getRequestCount();
                System.out.println("count = " + count);
                return urlDto;
            }
        }


        //response.sendError(HttpServletResponse.SC_NOT_FOUND);

        return null;
    }
}
