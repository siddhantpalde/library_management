package org.library_management.service;

import org.library_management.database.Data;
import org.library_management.entity.User;

import java.util.Base64;
import java.util.List;

public class LoginServiceImpl implements LoginService {

    @Override
    public User login(String username, String password) {
        User user = null;
        user = Data.checkLogin(username,password);

        if( user != null ){
            System.out.println("Login successful");
            return user;
        }
        return null;
    }
}
