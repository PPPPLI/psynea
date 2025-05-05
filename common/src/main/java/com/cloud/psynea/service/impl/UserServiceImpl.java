package com.cloud.psynea.service.impl;

import com.cloud.psynea.constant.ExceptionMessage;
import com.cloud.psynea.entity.User;
import com.cloud.psynea.exception.UserExistException;
import com.cloud.psynea.repository.UserRepository;
import com.cloud.psynea.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
