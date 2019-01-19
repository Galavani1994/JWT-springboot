package com.mahdi.springSecurityJWT2.service;

import com.mahdi.springSecurityJWT2.model.ApplicationUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser application = loadApplicationUserByUsername(username);
        return new User(application.getUsername(), application.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_USER"));
    }

    public ApplicationUser loadApplicationUserByUsername(String username) {

        return new ApplicationUser("batman", "pass");
    }
}
