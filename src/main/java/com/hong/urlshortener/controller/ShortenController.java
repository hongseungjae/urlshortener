package com.hong.urlshortener.controller;

import com.hong.urlshortener.dto.UrlDTO;
import com.hong.urlshortener.service.EncodeUrl;
import com.hong.urlshortener.service.ParseUrl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
    @ResponseBody
    public String urlConvert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ParseUrl parseUrl = getParseUrl(request);

        EncodeUrl encodeUrl = new EncodeUrl();
        String indexEncoded = encodeUrl.urlToShortenUrl(parseUrl.getUrl(),base62, listStorage);
        // 인덱스 인코딩

        String shorturl = encodeUrl.createUrl(request,indexEncoded);
        // 단축 URL 생성

        addOriginUrl(parseUrl, shorturl);

        return shorturl;


    }

    private static void addOriginUrl(ParseUrl parseUrl, String shorturl) {
        UrlDTO urlDto = new UrlDTO(parseUrl.getUrl(), shorturl);
        listStorage.add(urlDto);
    }

    private static ParseUrl getParseUrl(HttpServletRequest request) {
        ParseUrl parseUrl = new ParseUrl(request);
        parseUrl.setUrl(request.getParameter("url"));
        return parseUrl;
    }
}
