package com.cloud.psynea.security;

import com.cloud.psynea.dto.ResponseDto;
import com.cloud.psynea.utils.JwtUtil;
import com.cloud.psynea.utils.PrintResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserSecurity security = (UserSecurity) authentication.getPrincipal();
        String user = objectMapper.writeValueAsString(security.getUser());
        String longTermToken = JwtUtil.createToken(user, Instant.now().plus(1, ChronoUnit.DAYS));
        String shortTermToken = JwtUtil.createToken(user, Instant.now().plus(30, ChronoUnit.MINUTES));

        ResponseDto<List<String>> responseDto = new ResponseDto<>(HttpStatus.ACCEPTED,List.of(longTermToken,shortTermToken));

        PrintResponseUtil.returnResponse(response,responseDto);

    }
}
