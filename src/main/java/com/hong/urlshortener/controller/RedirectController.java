package com.hong.urlshortener.controller;

import com.hong.urlshortener.dto.UrlDTO;
import com.hong.urlshortener.service.DecodeUrl;
import com.hong.urlshortener.service.IndexUrl;
import com.hong.urlshortener.service.ParseUrl;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.net.URI;


import static com.hong.urlshortener.controller.IndexController.listStorage;
import static com.hong.urlshortener.controller.IndexController.base62;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class RedirectController {
    @RequestMapping(value = "/*/", method = GET)
    public ResponseEntity<?> urlRedirect(HttpServletRequest request, Model model) {

        ParseUrl parseUrl = new ParseUrl(request);
        parseUrl.pathExtract();  // http://localhost:8080/abcd/ -> /abcd/
        parseUrl.pathSplit();    // /abcd -> abcd

        DecodeUrl decodeUrl = new DecodeUrl();
        String indexDecoded = decodeUrl.urlToIndex(parseUrl.getUrl(),base62); // abcd -> 58

        IndexUrl indexUrl = new IndexUrl();

        if(indexUrl.integerCheck(indexDecoded) && indexUrl.nullCheck(indexDecoded)){ //널,숫자 체크
            int index = Integer.parseInt(indexDecoded);
            if(indexUrl.indexRangeCheck(index, listStorage)){  // arr 범위내에 인덱스
                UrlDTO urlDto = listStorage.get(index);
                String userUrl = urlDto.getOrginUrl(); // 원본 url 매칭
                urlDto.setRequestCount(urlDto.getRequestCount()+1);

                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(URI.create(userUrl));
                CacheControl cacheControl = CacheControl.noStore();
                headers.setCacheControl(cacheControl);
                return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY); // redirect 응답
            }
        }


        //그 외 매칭 안 될 경우 인덱스 페이지
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/hello/url"));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

}
