package com.cloud.psynea.security;

import com.cloud.psynea.dto.ResponseDto;
import com.cloud.psynea.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class LoginSuccessHandler{

    @Resource
    ObjectMapper objectMapper;

    public ResponseDto<List<Object>> onAuthenticationSuccess(Authentication authentication) throws IOException{

        UserSecurity security = (UserSecurity) authentication.getPrincipal();
        String user = objectMapper.writeValueAsString(security.getUser());
        String longTermToken = JwtUtil.createToken(user, Instant.now().plus(1, ChronoUnit.DAYS));
        String shortTermToken = JwtUtil.createToken(user, Instant.now().plus(30, ChronoUnit.MINUTES));

        return new ResponseDto<>(HttpStatus.ACCEPTED,List.of(longTermToken,shortTermToken,security.getUser().getNewUser()));

    }
}
