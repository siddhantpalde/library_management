package org.library_management.service;


import org.example.Main;
import org.example.entity.User;
import org.example.utility.InputReader;



public class StudentServiceImpl implements StudentService{
    @Override
    public void studentMenu(User user){
        BookService bookService = new BookServiceImpl();
        int choice;
        do {

            System.out.println("\n****STUDENT MENU****\n");
            System.out.println("1.Show Issued Books");
            System.out.println("2.Show Avaliable Books");
            System.out.println("4.Logout");
            choice = InputReader.getNumbers();

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
