package org.example;
import java.util.*;

import org.example.database.Data;
import org.example.entity.Book;
import org.example.entity.User;
import org.example.entity.UserType;
import org.example.service.*;

public class Main {

    public static void main(String[] args) {
        addInitialData();
        addAllUsers();
        mainLogin();

//TODO:
/*
* student menu
* Teacher Menu
* ADMIN : add analysis of books
* ADMIN : add add no of users by type of user
* ADMIN : add no of issued books
* ADMIN : add user should be only for user
* LIBRARIAN : add delete books
*
 * */
    }

    private static void adminMenu() {
        AdminService adminService = new AdminServiceImpl();
        adminService.menu();
    }

    private static void librarianMenu(){
        LibrarianService librarianService = new LibrarianServiceImpl();
        librarianService.menu();
    }
    private static void studentMenu(User user){
        StudentService studentService = new StudentServiceImpl();
        studentService.studentMenu(user);
    }
    private static User loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.next();

        System.out.println("Enter password:");
        String password = scanner.next();

        LoginService loginService = new LoginServiceImpl();
        return loginService.login(username,password);
    }

    private static void addAllUsers() {
        UserService userService = new UserServiceImpl();
        userService.addUser(new User("popatpalde","popatpalde", UserType.LIBRARIAN,
                "popatpalde@gmail.com"));
        userService.addUser(new User("pramilapalde","pramilapalde", UserType.TEACHER,
                "pramilapalde@gmail.com"));
        userService.addUser(new User("chetanpalde","chetanpalde", UserType.ADMIN,
                "chetanpalde@gmail.com"));
        userService.addUser(new User("siddhantpalde","siddhantpalde", UserType.STUDENT,
                "siddhantpalde@gmail.com"));
        userService.addUser(new User("mayuripalde","mayuripalde", UserType.STUDENT,
                "mayuripalde@gmail.com"));
    }
    private static void  addInitialData() {
        BookService bookService = new BookServiceImpl();
        bookService.addBook(new Book("PopatBook",450,"PopatPalde"));
        bookService.addBook(new Book("PramilaBook",320,"PramilaPalde"));
        bookService.addBook(new Book("ChetanBook",600,"ChetanPalde"));
        bookService.addBook(new Book("SiddhantBook",400,"SiddhantPalde"));
        bookService.addBook(new Book("MayuriBook",350,"MayuriPalde"));
        bookService.addBook(new Book("LucyBook",250,"LucyPalde"));

    }
    private static void addBook(){
        BookServiceImpl bookService = new BookServiceImpl();
        String bookName,authorName;


    }

    public static void mainLogin(){
        User user = loginUser();
        switch (user.getRole()) {
            case ADMIN: adminMenu();
                break;
            case LIBRARIAN: librarianMenu();
                break;
            case STUDENT: studentMenu(user);
                break;
            case TEACHER:
                break;

        }
    }
}
