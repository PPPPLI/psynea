package com.cloud.psynea.service;

import com.cloud.psynea.entity.User;

public interface UserService {

    User getUserByName(String userName);

    void addUser(User user);

    void updateUser(String userName);

}
