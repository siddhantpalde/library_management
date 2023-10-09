package org.example.service;

import org.example.entity.User;
import org.example.entity.UserType;

public interface UserService {
    void addUserDetails();

    void addUser(User user);


    UserType assignUserType(int role);

    void deleteUser();

    void printAllUsers();


    User searchUser();
}
