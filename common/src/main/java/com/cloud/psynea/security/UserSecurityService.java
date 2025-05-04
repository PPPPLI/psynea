package com.cloud.psynea.security;

import com.cloud.psynea.entity.User;
import com.cloud.psynea.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {

    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getUserByName(username);

        if(user == null){

            throw new UsernameNotFoundException("Please check your account");
        }

        List<SimpleGrantedAuthority> authorities = user.getAuthorities().stream().map(SimpleGrantedAuthority::new).toList();

        return UserSecurity.builder()
                .user(user)
                .authorities(authorities)
                .build();
    }
}
