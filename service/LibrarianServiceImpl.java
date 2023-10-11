package org.library_management.service;

import org.library_management.Main;

import java.util.Scanner;

public class LibrarianServiceImpl implements LibrarianService{
    @Override
    public void menu(){
        Scanner scanner = new Scanner(System.in);
        BookService addMultipleService = new BookServiceImpl();
        int choice;
        do {
            System.out.println("1.Search Book");
            System.out.println("2.Add Book");
            System.out.println("3.Print all Books");
            System.out.println("4.Print all issued Books");
            System.out.println("5:Issue Book");
            System.out.println("6.Delete User");
            System.out.println("7.Logout");

            choice = scanner.nextInt();

            switch (choice){//return book remaining
                case 1:
                    addMultipleService.searchBookInRange();
                    break;
                case 2 :
                    addMultipleService.addMultipleBooks();
                    break;
                case 3:
                    addMultipleService.printBooks();
                    break;
                case 4:addMultipleService.issuedBooks();
                    break;
                case 5:addMultipleService.issueBook();
                    break;
                case 6:addMultipleService.deleteUser();
                    break;
                case 7:
                    Main.mainLogin();
//                default:
//                    System.out.println("Wrong Choice!!!!!!!");
//                    break;
            }
        }while(choice < 7);
    }

}
