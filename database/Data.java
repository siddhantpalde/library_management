package org.example.database;

import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.User;
import org.example.entity.UserType;
import org.example.service.AdminService;
import org.example.service.AdminServiceImpl;
import org.example.utility.InputReader;

import java.sql.*;
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
    public static void delBook(int index) throws SQLException {
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

    //SQL QUERIES
    private static void connectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        try {

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/trial", "root", "asda"
            );
            createUserDb(connection);
            createBookDb(connection);
            createIssuedBookDb(connection);
            // Perform database operations here
             // Close the connection when done
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }

    private static void createUserDb(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS " +
                "users(" +
                "uId VARCHAR(20) PRIMARY KEY," +
                "uName VARCHAR(200) not null," +
                "uEmail VARCHAR(50)," +
                "uPassword VARCHAR(50),"+
                "uRole VARCHAR(20)"+
                ")";

        //create a statement
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }
    private static void createBookDb(Connection connection)throws SQLException{
        String query = "CREATE TABLE IF NOT EXISTS " +
                "books(" +
                "bId VARCHAR(20) PRIMARY KEY," +
                "bName VARCHAR(200) NOT NULL," +
                "bPrice INT,"+
                "bAuthor VARCHAR(20)"+
                ")";
        Statement statement = connection.createStatement();
        statement.execute(query);
    }
    private static void createIssuedBookDb(Connection connection) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS "+
                "issuedBooks("+
                "bId VARCHAR(50) PRIMARY KEY ,"+
                "FOREIGN KEY (bId) REFERENCES books(bId),"+
                "uId VARCHAR(50),"+
                "FOREIGN KEY (uId) REFERENCES users(uId)" +
                ")";
        Statement statement = connection.createStatement();
        statement.execute(query);
    }
    public static void initiateDb(){
        connectDb();
    }
    //--------INSERT DATA IN SQL DATABASE
    public static void insertUser(User user){
        insertUserToDB(user);
    }
    private static void insertUserToDB(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/trial",
                    "root",
                    "asda"
            );
            String query = "INSERT INTO users(uId,uName,uEmail,uPassword,uRole) values(?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            String uId = user.getUserId();
            String uName = user.getUsername();
            String uPassword = user.getPassword();
            String uEmail = user.getEmailId();
            String uRole = user.getRole().toString();

            pstmt.setString(1,uId);
            pstmt.setString(2,uName);
            pstmt.setString(3,uEmail);
            pstmt.setString(4,uPassword);
            pstmt.setString(5,uRole);

            pstmt.executeUpdate();
            System.out.println("\n!!!!! User Succesfully added !!!\n"
                    +"\nUser Id : "+uId
                    +"\nUser Name : "+uName
                    +"\nUser Email : " +uEmail
                    +"\nUser Type : "+uRole
            );
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User checkLogin(String userName, String userPassword){

         return validateCredentials(userName,userPassword);
    }
    private static User validateCredentials(String userName, String userPassword){

        User user = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/trial",
                    "root",
                    "asda"
            );
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE uName LIKE '%"+userName+"%'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){

                String name = resultSet.getString("uName");
                String password = resultSet.getString("uPassword");
                String id = resultSet.getString("uid");
                String email = resultSet.getString("uEmail");
                UserType role = UserType.valueOf(resultSet.getString("uRole"));

                if ((name).equals(userName) && (password).equals(
                                Base64.getEncoder().encodeToString(userPassword.getBytes()))){

                    System.out.println(" \n!!!! User Found !!!!!!!!\n");
                    user = new User(name,password,role,email);
                    user.setUserId(id);
                    return user;
                }

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void delUserFromDb() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/trial",
                    "root",
                    "asda"
            );

            printAllUsers();
            System.out.println("hello");
            System.out.print("ENTER ID YOU WANT TO DELETE : ");
            String userId = scanner.next();
            System.out.println("hello");
            getUserDetails(userId);

            String query = "DELETE FROM users WHERE uid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,userId);
            int rowDeleted = preparedStatement.executeUpdate();

            if (rowDeleted > 0){
                System.out.println("!!!! User Deleted !!!!");
            }
            else {
                System.out.println("!!!! User Not Found !!!!");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    public static void showUserById(Connection connection) throws SQLException {
//        Statement statement = connection.createStatement();
//        String rQuery = "SELECT * FROM = ?";
//        PreparedStatement pstmt = connection.prepareStatement(rQuery);
//
//        ResultSet resultSet = statement.executeQuery(rQuery);
//        String name = resultSet.getString("uName");
//        String password = resultSet.getString("uPassword");
//        String id = resultSet.getString("uid");
//        String email = resultSet.getString("uEmail");
//        UserType role = UserType.valueOf(resultSet.getString("uRole"));
//
//        System.out.println("\n!!!!! User Succesfully added !!!\n"
//                +"\nUser Id : "+id
//                +"\nUser Name : "+name
//                +"\nUser Email : " +email
//                +"\nUser Type : "+role
//        );
public static void getUserDetails(String uId) throws SQLException {
    System.out.println("hello");
    try{Class.forName("com.mysql.cj.jdbc.Driver");}catch (ClassNotFoundException e){e.printStackTrace();}
    try{
        Connection connection = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/trial",
        "root",
        "asda"
);
        String query = "SELECT * FROM users WHERE uid = ? ";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1,uId);
        ResultSet resultSet = pstmt.executeQuery();
        while (resultSet.next()) {

            String id = resultSet.getString("uid");
            String name = resultSet.getString("uName");
            String email = resultSet.getString("uEmail");
            UserType role = UserType.valueOf(resultSet.getString("uRole"));

            System.out.println("____________________________________________________________________________________\n"+"| "
                    +id+" | "
                    +name+" | "
                    +email+" | "
                    +role+" |"
            );
        }
        System.out.println("____________________________________________________________________________________\n");

}catch (SQLException e){
    e.printStackTrace();
}
    System.out.println("hello");
}

//    }
    public static void printAllUsers() {
        print();
    }
    private static void print(){
        try{
                Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/trial",
                    "root",
                    "asda"
            );
            String query = "SELECT * from users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                String id = resultSet.getString("uid");
                String name = resultSet.getString("uName");
                String email = resultSet.getString("uEmail");
                UserType role = UserType.valueOf(resultSet.getString("uRole"));

                System.out.println("____________________________________________________________________________________\n"+"| "
                        +id+" | "
                        +name+" | "
                        +email+" | "
                        +role+" |"
                );
            }
            System.out.println("____________________________________________________________________________________\n");
            }
            catch (SQLException e){
                e.printStackTrace();
        }
    }
}
