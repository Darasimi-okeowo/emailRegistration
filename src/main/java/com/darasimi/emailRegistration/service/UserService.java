package com.darasimi.emailRegistration.service;

import com.darasimi.emailRegistration.entity.User;

public interface UserService {
    User saveUser(User user);
    Boolean verifyToken(String token);
}
