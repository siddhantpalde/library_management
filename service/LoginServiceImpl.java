package org.example.service;

import org.example.database.Data;
import org.example.entity.User;

import java.util.Base64;
import java.util.List;

public class LoginServiceImpl implements LoginService {

    @Override
    public User login(String username, String password) {
        List<User> users = Data.getUsers();
        for(User user : users) {
            if(user.getUsername().equals(username)) {
                if(user.getPassword().equals(
                        Base64.getEncoder().encodeToString(password.getBytes()))) {
                    System.out.println("Login successful");
                    return user;
                }
            }
        }
        System.out.println("Wrong credentials");
        return null;
    }
}
