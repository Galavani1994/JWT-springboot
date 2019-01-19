package com.mahdi.springSecurityJWT2.endpoint;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.Principal;

import static com.mahdi.springSecurityJWT2.security.SecurityConstans.HEADER_STRING;


@RestController
@RequestMapping("/v1/floor1/")
public class FirstFloorEndpoint {

    @GetMapping("office1")
    public String enterOffice1(HttpServletRequest request, HttpServletResponse response, Authentication authentication,
                               Principal principal){
       try{
           String ss=authentication.getName();
           System.out.println(ss);
           System.out.println(principal.getName());
       }catch (Exception e){
           System.out.println(e.getMessage());

       }
       return "okkkkkkkkk";
    }


    @GetMapping("office2")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<?> enterOffice2(){
        return new ResponseEntity<>("you are inside office2", HttpStatus.OK);
    }

    @GetMapping("/show")
    public ModelAndView showHoamePage(){
       ModelAndView mv= new ModelAndView("indexPage");
        return mv;

    }
}
