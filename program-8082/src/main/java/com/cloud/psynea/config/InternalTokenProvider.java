package com.cloud.psynea.config;

import com.cloud.psynea.utils.JwtUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class InternalTokenProvider {
    private String token;

    @PostConstruct
    public void init() {
        token = JwtUtil.createInternalTokenForServer();
    }

    public String getToken() {
        return token;
    }
}
