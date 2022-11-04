package com.hong.urlshortener.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hong.urlshortener.controller.indexController.arr;
import static com.hong.urlshortener.controller.indexController.base62;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class shortenController {
    @RequestMapping(value = "/urlConvert", method = POST)
    public ModelAndView urlConvert(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        System.out.println("urlConvert 시작");
        String userUrl = request.getParameter("url");
        System.out.println("userUrl = " + userUrl);


        String index = arr.size()+"";
        byte[] Encoded = base62.encode(index.getBytes());
        String indexEncoded = new String(Encoded);
        System.out.println("인코딩된 index = " + indexEncoded);

        System.out.println(request.getRequestURL().toString().replace(request.getRequestURI(),"/"+indexEncoded));
        arr.add(userUrl);

        ModelAndView mv = new ModelAndView();

        mv.addObject("url",request.getRequestURL().toString().replace(request.getRequestURI(),"/"+indexEncoded));
        mv.setViewName("short");

        return mv;
    }
}
