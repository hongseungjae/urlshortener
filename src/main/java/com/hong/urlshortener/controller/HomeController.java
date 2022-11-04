package com.hong.urlshortener.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class HomeController {



    @RequestMapping(value = "/*", method = GET)
    public String urlRedirect(HttpServletRequest request, Model model) {

        System.out.println("RequestMethod.GET");
        //String id = request.getParameter("id");
        //System.out.println("id : " + id);
        //model.addAttribute("studentId", id);
        System.out.println(request.getRequestURI());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        ResponseEntity<?> re = new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);


        return "student/student2";
    }

    @RequestMapping("/ss")
    @ResponseBody
    public ResponseEntity<?> redirect() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        return new ResponseEntity<String>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

}
