# Java STUDENT  App (Console-Based)

A simple Java console-based Student application using MySQL database for persistent storage.

## Feature

- Add students with name, department, year 
- View all Students
- Update students
- Delete students
- MySQL database connectivity using JDBC

## Database Setup

1. Make sure MySQL is installed and running.
2. Create a database named `todo_db`:
    ```sql
    CREATE DATABASE todo_db;
    ```
3. Create a table named `stud`:
    ```sql
    CREATE TABLE IF NOT EXISTS students (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100)," +
                "department VARCHAR(50)," +
                "year INT," +
                "email VARCHAR(100));
    ```

## Database Connection

Update your database credentials in `DatabaseHelper.java`:
```java
String url = "jdbc:mysql://localhost:3306/todo_db";
String user = "root"; // Your MySQL username
String password = "Ajay"; // Your MySQL password
