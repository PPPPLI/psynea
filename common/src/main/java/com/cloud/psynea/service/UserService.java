package com.cloud.psynea.service;

import com.cloud.psynea.entity.User;
import org.springframework.security.core.Authentication;

public interface UserService {

    User getUserByName(String userName);

    void addUser(User user);

}
