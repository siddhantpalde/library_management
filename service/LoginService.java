package org.library_management.service;

import org.library_management.entity.User;
import org.library_management.entity.UserType;

public interface LoginService {
    User login(String username, String password);
}
