package com.sso.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    private RestTemplate restTemplate;

    private final String CART_INFO_ADDRESS = "http://login.xiaoming.com:9000/login/info?token=";

    @GetMapping("/index")
    public String toCart(@CookieValue(required = false,value = "TOKEN")Cookie cookie, HttpSession session){
        if(cookie != null){
            String token = cookie.getValue();
            if(!StringUtils.isEmpty(token)){
                Map result = restTemplate.getForObject(CART_INFO_ADDRESS + token, Map.class);
                session.setAttribute("loginUser",result);
            }
        }
        return "index";
    }

    @GetMapping("/exit")
    public String outCart(@CookieValue(required = false,value = "TOKEN")Cookie cookie, HttpSession session, HttpServletResponse response){
        cookie.setMaxAge(0);
        cookie.setDomain("xiaoming.com");
        cookie.setPath("/");
        response.addCookie(cookie);
        session.invalidate();
//        Map result = restTemplate.getForObject(CART_INFO_ADDRESS + cookie, Map.class);
//        session.setAttribute("loginUser",null);
        return "redirect:index";
    }


}
