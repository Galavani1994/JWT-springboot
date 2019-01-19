package com.mahdi.springSecurityJWT2.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MainController {

    @GetMapping("/logPage")
    public ModelAndView logPage(){
        ModelAndView mv=new ModelAndView("login");
        return mv;
    }
    @GetMapping("/hello")
    public ModelAndView hello(HttpServletRequest request, HttpServletResponse response){
        String s=request.getHeader("Accept-Language");
        System.out.println(s);
        response.setHeader("testhesder","writed by mahdi");
        return new ModelAndView("indexPage");
    }

}
