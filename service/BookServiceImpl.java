package org.example.service;

import java.util.*;
import org.example.database.Data;
import org.example.entity.Book;
import org.example.entity.User;
import org.example.utility.InputReader;


public class BookServiceImpl implements BookService {

    @Override
    public Book addBook(Book book) {
        String newBookId = "Book_"+System.nanoTime();
        book.setBookId(newBookId);
        return Data.addBookToDb(book);
    }

    @Override
    public void printBooks() {
        Data.printBooks();
    }
    @Override
    public void searchBookInRange(){
        List<Book> bookList = Data.getBooks();
        for(Book book : bookList){
            if( book.getBookPrice()>=300 && book.getBookPrice()<=500){
                System.out.println(book);
            }
        }
    }
    @Override
    public void addMultipleBooks() {

        BookServiceImpl bookService = new BookServiceImpl();
        String bookName,authorName;
        double price;


        System.out.println("Enter Book Name : ");
        bookName = InputReader.getString();
       // System.out.println(bookName);

        System.out.println("Enter Author Name : ");
        authorName = InputReader.getString();
      //  System.out.println(authorName);

        System.out.println("Enter Price of Book : ");
        price = InputReader.getDoubleNumbers();
        bookService.addBook(new Book(bookName,price,authorName));
    }

    @Override
    public void addMyltipleBooks() {

    }
    @Override
    public void searchBookById(){
        boolean flag = false;
        User user;
        UserService userService = new UserServiceImpl();
        List<Book> books = Data.getBooks();

        System.out.print("Enter Book Name : \n");
        String bookName = InputReader.getString();
        System.out.println("BookName : "+bookName);
        char ch;

        for (Book book : books ){
            if ((book.getBookName()).equals(bookName)){
                flag = true;
                System.out.println("Do you want to issue this book (y/n) : \n");
                ch = InputReader.getString().charAt(0);

                user = userService.searchUser();
                System.out.println("UserName :"+ user.getUsername());
                if (( ch == 'y' )||( ch == 'Y' )){
                    Data.setIssueBook(book.getBookId(),user);
                    ch='n';
                }else {
                    break;
                }
            }
        }
        if (!flag){
            System.out.println("Book Not Found");
        }

    }
    @Override
    public void issueBook(){
        searchBookById();
    }
    @Override
    public void issuedBooks(){
        Data.printIssueBook();
    }
    @Override
    public void showIssuedBooks(){
        Data.printIssueBook();
    }
    @Override
    public void deleteUser(){
        System.out.println("Enter User Id : ");
        String userId = InputReader.getString();
        Data.removeUser(userId);
    }
    @Override
    public void issuedByStudent(User user){
        Data.issuedByMe(user);
    }
}
