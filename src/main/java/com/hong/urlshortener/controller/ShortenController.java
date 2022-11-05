package com.hong.urlshortener.controller;

import com.hong.urlshortener.service.EncodeUrl;
import com.hong.urlshortener.service.ParseUrl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hong.urlshortener.controller.IndexController.arr;
import static com.hong.urlshortener.controller.IndexController.base62;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ShortenController {
    @RequestMapping(value = "/urlConvert", method = POST)
    public ModelAndView urlConvert(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {

        ParseUrl parseUrl = new ParseUrl(request);
        parseUrl.setUrl(request.getParameter("url"));


        EncodeUrl encodeUrl = new EncodeUrl();
        String indexEncoded = encodeUrl.urlToShortenUrl(parseUrl.getUrl(),base62,arr);

        arr.add(parseUrl.getUrl());

        ModelAndView mv = new ModelAndView();

        mv.addObject("url",encodeUrl.createUrl(request,indexEncoded));
        mv.setViewName("short");

        return mv;
    }
}
