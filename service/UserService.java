package org.library_management.service;

import org.library_management.entity.User;
import org.library_management.entity.UserType;

import java.sql.SQLException;

public interface UserService {
    void addUserDetails();

    void addUser(User user);


    UserType assignUserType(int role);

    void deleteUser() throws SQLException;

    void printAllUsers();


    User searchUser();

    void printUsers();
}
