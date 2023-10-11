package org.library_management.service;

import org.library_management.Main;
import org.library_management.entity.User;

import java.util.Scanner;

public class StudentServiceImpl implements StudentService{
    @Override
    public void studentMenu(User user){
        Scanner scanner = new Scanner(System.in);
        BookService bookService = new BookServiceImpl();
        int choice;
        do {

            System.out.println("\n****STUDENT MENU****\n");
            System.out.println("1.Show Issued Books");
            System.out.println("2.Show Avaliable Books");
            System.out.println("4.Logout");
            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    bookService.issuedByStudent(user);
                    break;
                case 2:
                    bookService.printBooks();
                    break;
                case 3:
                    bookService.issueBook();
                    break;
                case 4:
                    Main.mainLogin();
            }
        }while(choice < 5);
    }
}
