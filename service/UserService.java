package org.library_management.service;

import org.library_management.entity.User;
import org.library_management.entity.UserType;

public interface UserService {
    void addUserDetails();

    void addUser(User user);


    UserType assignUserType(int role);

    void deleteUser();

    void printAllUsers();


    User searchUser();
}
