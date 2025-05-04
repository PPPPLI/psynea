package com.cloud.psynea.controller;

import com.cloud.psynea.dto.ResponseDto;
import com.cloud.psynea.dto.UserDto;
import com.cloud.psynea.entity.User;
import com.cloud.psynea.mapper.UserMapper;
import com.cloud.psynea.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private UserService userService;

    @Resource
    UserMapper userMapper;

    @PostMapping("/register")
    public ResponseDto<String> register(@RequestBody UserDto userDto) {

        userService.addUser(userMapper.userDtotoUser(userDto));

        return new ResponseDto<>(HttpStatus.OK,"Account created successfully");
    }
}
