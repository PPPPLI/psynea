package com.cloud.psynea.filter;

import com.cloud.psynea.dto.ResponseDto;
import com.cloud.psynea.entity.User;
import com.cloud.psynea.utils.JwtUtil;
import com.cloud.psynea.utils.PrintResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Component
@Slf4j
public class WebSecurityFilter extends OncePerRequestFilter {

    @Resource
    ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String url = request.getRequestURI();

        if(url.equals("/auth/login") || url.equals("/auth/register")){

            filterChain.doFilter(request, response);
        }

        String token = request.getHeader("Authorization");


        if(token == null || !token.startsWith("Bearer ")){

            PrintResponseUtil.returnResponse(response,ResponseDto.builder().status(HttpStatus.UNAUTHORIZED).data("Invalid Token").build());
            return;
        }

        token = token.replace("Bearer ","");

        if(!JwtUtil.verify(token)){

            PrintResponseUtil.returnResponse(response,ResponseDto.builder().status(HttpStatus.UNAUTHORIZED).data("Invalid Token").build());
            return;
        }

        User user = objectMapper.readValue(JwtUtil.extractToken(token), User.class);

        List<SimpleGrantedAuthority> authorities = user.getAuthorities().stream().map(SimpleGrantedAuthority::new).toList();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        log.info("{} - Token is verified", LocalDateTime.now());
        filterChain.doFilter(request, response);
    }

}
