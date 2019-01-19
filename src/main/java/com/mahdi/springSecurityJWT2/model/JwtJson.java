package com.mahdi.springSecurityJWT2.model;

import lombok.Data;

import java.util.Date;

@Data
public class JwtJson {
    private String access_token;
    private String token_type;
    private Date expire_in;
    private ApplicationUser applicationUser;

    public JwtJson() {
    }

    public JwtJson(String access_token, String token_type, Date expire_in) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expire_in = expire_in;
    }

    public JwtJson(String access_token, String token_type, Date expire_in, ApplicationUser applicationUser) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expire_in = expire_in;
        this.applicationUser = applicationUser;
    }


}
