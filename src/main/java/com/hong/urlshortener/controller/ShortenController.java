package com.hong.urlshortener.controller;

import com.hong.urlshortener.dto.UrlDTO;
import com.hong.urlshortener.service.EncodeUrl;
import com.hong.urlshortener.service.ParseUrl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hong.urlshortener.controller.IndexController.listStorage;
import static com.hong.urlshortener.controller.IndexController.base62;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ShortenController {
    @RequestMapping(value = "/urlConvert", method = POST)
    public String urlConvert(@RequestBody String shorturl, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ParseUrl parseUrl = new ParseUrl(request);
        parseUrl.setUrl(request.getParameter("url"));


        EncodeUrl encodeUrl = new EncodeUrl();
        String indexEncoded = encodeUrl.urlToShortenUrl(parseUrl.getUrl(),base62, listStorage);

        shorturl = encodeUrl.createUrl(request,indexEncoded);


        UrlDTO urlDto = new UrlDTO(request.getParameter("url"),shorturl);
        listStorage.add(urlDto);

        return shorturl;


    }
}
