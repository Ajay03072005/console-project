# Java TODO List App (Console-Based)

A simple Java console-based TODO List application using MySQL database for persistent storage.

## Feature

- Add tasks with descriptions
- View all tasks
- Update task status
- Delete tasks
- MySQL database connectivity using JDBC

## Database Setup

1. Make sure MySQL is installed and running.
2. Create a database named `todo_db`:
    ```sql
    CREATE DATABASE todo_db;
    ```
3. Create a table named `tasks`:
    ```sql
    CREATE TABLE tasks (
        id INT PRIMARY KEY AUTO_INCREMENT,
        title VARCHAR(255) NOT NULL,
        description TEXT,
        status VARCHAR(50) DEFAULT 'Pending'
    );
    ```

## Database Connection

Update your database credentials in `DatabaseHelper.java`:
```java
String url = "jdbc:mysql://localhost:3306/todo_db";
String user = "root"; // Your MySQL username
String password = "Ajay"; // Your MySQL password
