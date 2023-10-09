package org.example.database;

import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.User;
import org.example.service.AdminService;
import org.example.service.AdminServiceImpl;
import org.example.utility.InputReader;

import java.util.*;

public class Data {
    private static List<Book> books = new ArrayList<>();
    private static List<Author> authors = new ArrayList<>();
    private static List<User> users = new ArrayList<>();

    private static Map<String,User> issueBook = new HashMap<String,User>();

    private static Map<String , Integer> bookQuantity = new HashMap<String,Integer>();
    public static List<Book> getBooks() {
        return books;
    }

//    public static Double getBookPrice(){ return books.bookPrice;}
    public static Book addBookToDb(Book book) {
        try {
            books.add(book);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return book;
    }
    public static List<Author> getAuthors() {
        return authors;
    }

    public static void setAuthors(List<Author> authors) {
        Data.authors = authors;
    }

    public static void printBooks() {
        for(Book book : books) {
            System.out.println(book);
        }
    }

    public static User addUser(User user) {
        users.add(user);
        return user;
    }

    public static List<User> getUsers() {
        return users;
    }
    public static void delBook(int index){
        AdminService adminService = new AdminServiceImpl();
        books.remove(index);
        System.out.print("Book Deleted successfully !!!!!");
        adminService.menu();
    }


    public static Map<String, User> getIssueBook() {
        return issueBook;
    }

    public static void setIssueBook(Map<String, User> issueBook) {
        Data.issueBook = issueBook;
    }

    public static void printIssueBook(){
        if(issueBook.size() == 0){
            System.out.println("No Books Issued ");
        }else {
            for (Map.Entry<String, User> entry : issueBook.entrySet()){
                System.out.println("Book id : "+ entry.getKey()+"\nStudent\nName : "+entry.getValue());
            }
        }
    }
    public static Boolean checkUserByEmail(String email){
        boolean flag = false;
        for(User user : users){
            if((email).equals(user.getEmailId())){
                System.out.println("User already present with same Email Id");
                return flag = true;
            }
        }
        return false;
    }
    public static void delUserFromDb(String userId){
        boolean flag = false;
        User tempUser = new User();
        for (User user : users){
            if((userId).equals(user.getUserId())){
                tempUser = user;
                flag = true;
            }
        }
        if(flag){
            if(users.remove(tempUser)){
                System.out.println(
                        "User \n"
                                +tempUser.getUserId()
                                + "\n"+tempUser.getUsername()
                                +"\n"+tempUser.getEmailId()
                                +"\n"+tempUser.getRole()
                                +"\n is removed Succcesfully !!!!");
            }
        }else {
            System.out.println("User Not Found !!!!");
        }
    }

    public static void setIssueBook(String bookId, User user) {
        if(!searchBook(bookId)){
            issueBook.put(bookId,user);
        }else {
            System.out.println("Book already Issued use another Book");
        }
    }

    private static boolean searchBook(String key) {
        for (Map.Entry<String,User> entry : issueBook.entrySet()){
            if((entry.getKey()).equals(key)){
                return true;
            }
        }
        return false;
    }
    public static void removeUser(String userId){
        String choice;
        User user1 = null;
        for (User user : users){
            if ((user.getUserId()).equals(userId)){
                user1 = user;
            }
        }
        System.out.println("Do you want to delete this User : (y/n)\n"
                +"User Name : "+user1.getUsername()
                +"\nEmail Id : "+user1.getEmailId()
                +"\nUser Type : "+user1.getRole());
        choice = InputReader.getString();
        if( choice.charAt(0) == 'y' || choice.charAt(0) == 'Y' ){
            delUser(user1);
        }
    }
    private static void delUser(User user){
        users.remove(user);
        System.out.println("User Succesfully removed");
    }
    private static void searchIssued(User user){
        User userr;
        Book book1 = new Book();
        for(Map.Entry<String,User> entry : issueBook.entrySet()){
            userr = entry.getValue();
            if((user.getUserId()).equals(userr.getUserId())){
                System.out.println("Book : \nBook Id : "+entry.getKey()+"\nUser : \n"+entry.getValue());

            }
        }
    }
    public static void issuedByMe(User user){
        searchIssued(user);
    }
}
