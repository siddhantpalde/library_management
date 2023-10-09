package org.example.service;

import org.example.entity.Book;
import org.example.entity.User;

public interface BookService {
    Book addBook(Book book);

    void printBooks();

    void addMultipleBooks();

    void addMyltipleBooks();
    void searchBookInRange();

    void searchBookById();

    void issueBook();

    void issuedBooks();

    void showIssuedBooks();

    void deleteUser();

    void issuedByStudent(User user);
}
