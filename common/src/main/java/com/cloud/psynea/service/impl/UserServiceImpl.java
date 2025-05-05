package com.cloud.psynea.service.impl;

import com.cloud.psynea.constant.ExceptionMessage;
import com.cloud.psynea.dto.ResponseDto;
import com.cloud.psynea.entity.User;
import com.cloud.psynea.exception.UserExistException;
import com.cloud.psynea.repository.UserRepository;
import com.cloud.psynea.security.LoginSuccessHandler;
import com.cloud.psynea.service.UserService;
import com.cloud.psynea.utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Resource
    PasswordEncoder passwordEncoder;


    @Override
    public User getUserByName(String userName) {

        return userRepository.findUserByUsername(userName);
    }

    @Override
    public void addUser(User user) {

        User res = userRepository.findUserByUsername(user.getUsername());

        if(res != null) {

            throw new UserExistException(ExceptionMessage.USER_EXIST_EXCEPTION);
        }

        user.setPasswd(passwordEncoder.encode(user.getPasswd()));

        userRepository.save(user);
    }


}
