package com.hong.urlshortener.controller;

import com.hong.urlshortener.utill.Base62;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class indexController {

    static ArrayList<String> arr = new ArrayList<>();
    static Base62 base62 = Base62.createInstance();


    @RequestMapping(value = "/hello/url", method = GET)
    public String main(HttpServletRequest request, Model model) {
        System.out.println("index");
        return "hello";
    }
}
