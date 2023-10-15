package org.library_management.service;

public interface LibrarianService {

    //I must be overridden
    public abstract void menu();

    static void myMethod() {
        System.out.println("I cannot be overridden");
    }

    default  void defaultMethod() {
        System.out.println("I am a default method. I can be overridden");
    }
}
