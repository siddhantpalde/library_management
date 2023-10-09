package org.example.service;

import org.example.entity.User;
import org.example.entity.UserType;

public interface LoginService {
    User login(String username, String password);
}
