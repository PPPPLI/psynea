package com.cloud.psynea.controller;

import com.cloud.psynea.dto.ResponseDto;
import com.cloud.psynea.dto.UserDto;
import com.cloud.psynea.mapper.UserMapper;
import com.cloud.psynea.security.LoginSuccessHandler;
import com.cloud.psynea.service.UserService;
import com.cloud.psynea.service.impl.LoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Resource
    private UserService userService;

    @Resource
    LoginService loginService;

    @Resource
    UserMapper userMapper;

    @Resource
    LoginSuccessHandler loginSuccessHandler;

    @PostMapping("/register")
    public ResponseDto<String> register(@RequestBody UserDto userDto) {

        userService.addUser(userMapper.userDtotoUser(userDto));

        return new ResponseDto<>(HttpStatus.OK,"Account created successfully");
    }

    @PostMapping("/login")
    public ResponseDto<List<Object>> login(@RequestBody UserDto userDto) throws IOException {

        Authentication authentication = loginService.login(userMapper.userDtotoUser(userDto));

        ResponseDto<List<Object>> res = loginSuccessHandler.onAuthenticationSuccess(authentication);

        log.info("{}- Long term token: {}", LocalDateTime.now(),res.getData());

        return res;
    }
}
