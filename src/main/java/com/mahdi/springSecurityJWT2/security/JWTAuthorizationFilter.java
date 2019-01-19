package com.mahdi.springSecurityJWT2.security;


import com.mahdi.springSecurityJWT2.model.ApplicationUser;
import com.mahdi.springSecurityJWT2.service.CustomeUserDetailService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.mahdi.springSecurityJWT2.security.SecurityConstans.HEADER_STRING;
import static com.mahdi.springSecurityJWT2.security.SecurityConstans.SECRET;
import static com.mahdi.springSecurityJWT2.security.SecurityConstans.TOKEN_PREFIX;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final CustomeUserDetailService customeUserDetailService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  CustomeUserDetailService customeUserDetailService) {
        super(authenticationManager);
        this.customeUserDetailService=customeUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
       String header=request.getHeader(HEADER_STRING);

       if (header == null || !header.startsWith(TOKEN_PREFIX))
       {
           chain.doFilter(request,response);
           return;
       }

       UsernamePasswordAuthenticationToken usernamepasswordAuth=getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(usernamepasswordAuth);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request){
        String token=request.getHeader(HEADER_STRING);
        if (token == null)
        {
            return null;
        }
        String username=Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                .getBody()
                .getSubject();

        UserDetails userDetails=customeUserDetailService.loadUserByUsername(username);
        ApplicationUser applicationUser=customeUserDetailService.loadApplicationUserByUsername(username);

        return username !=null ? new UsernamePasswordAuthenticationToken(applicationUser,null,userDetails.getAuthorities()):null;
    }
}
