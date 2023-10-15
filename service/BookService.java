package org.library_management.service;

import org.library_management.entity.Book;
import org.library_management.entity.User;

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
