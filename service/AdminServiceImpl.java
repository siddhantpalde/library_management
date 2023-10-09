package org.example.service;

import org.example.Main;

import java.util.Scanner;

public class AdminServiceImpl implements AdminService {
    @Override
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        BookService addMultipleService = new BookServiceImpl();
        UserService userService = new UserServiceImpl();
        int choice;
        do {
            System.out.println("1.Add new User");
            System.out.println("2.Delete a User");
            System.out.println("3.Show all Users");
            System.out.println("4.Print all Issued Books");
            System.out.println("5.Logout");
            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    userService.addUserDetails();
                    break;
                case 2 :
                    userService.deleteUser();
                    break;
                case 3:
                    userService.printAllUsers();
                    break;
                case 4:
                    addMultipleService.showIssuedBooks();
                    break;
                case 5 :
                    Main.mainLogin();
            }
        }while(choice < 6);
    }

}
