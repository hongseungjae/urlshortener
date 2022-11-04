package com.hong.urlshortener.controller;

import com.hong.urlshortener.utill.Base62;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.SimpleTimeZone;
import java.util.logging.Logger;

import static com.hong.urlshortener.controller.indexController.arr;
import static com.hong.urlshortener.controller.indexController.base62;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class redirectController {
    @RequestMapping(value = "/**", method = GET)
    public ResponseEntity<?> urlRedirect(HttpServletRequest request, Model model) {

        System.out.println("urlRedirect 시작");
        System.out.println("단축 url = " + request.getRequestURL());
        System.out.println("파싱할 부분 = " + request.getRequestURI());

        // 파싱
        String parseUrl = request.getRequestURI();
        parseUrl = parseUrl.substring(1);

        for(int i = 0; i < 100; i++){
            ArrayList arr = new ArrayList();
        }

        System.out.println("파싱 후 = "+parseUrl);

        //디코딩
        byte[] decoded = base62.decode(parseUrl.getBytes());

        String indexDecoded =new String(decoded);

        // indexDecoded 숫자인지, 인덱스 범위인지
        if(indexDecoded != null && !indexDecoded.matches("-?\\d+")) {
            System.out.println("indexDecoded 정수 아님");
        }else{
            int index = Integer.parseInt(indexDecoded);
            if( 0 <= index && index < arr.size()){ // 범위체크
                System.out.println("매칭");

                // if 숫자이고 , 범위내에 있을 경우
                String userUrl = arr.get(index);

                HttpHeaders headers = new HttpHeaders();
                //headers.setLocation(URI.create("/abcd"));
                headers.setLocation(URI.create(userUrl));
                //ResponseEntity<?> re = new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
                return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
            }else{
                System.out.println("범위에 없음");
            }


        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }




}
