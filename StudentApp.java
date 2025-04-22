import java.sql.*;
import java.util.Scanner;

public class StudentApp {
    private static Connection connection;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            connection = DatabaseHelper.connect();
            createTable();

            while (true) {
                showMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> listStudents();
                    case 3 -> searchStudent();
                    case 4 -> updateStudent();
                    case 5 -> deleteStudent();
                    case 6 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option, try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100)," +
                "department VARCHAR(50)," +
                "year INT," +
                "email VARCHAR(100))";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void showMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. List All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addStudent() throws SQLException {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter department: ");
        String department = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        String sql = "INSERT INTO students (name, department, year, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setInt(3, year);
            stmt.setString(4, email);
            stmt.executeUpdate();
            System.out.println("Student added successfully.");
        }
    }

    private static void listStudents() throws SQLException {
        String sql = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Student List ---");
            while (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Dept: %s | Year: %d | Email: %s\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getInt("year"),
                        rs.getString("email"));
            }
        }
    }

    private static void searchStudent() throws SQLException {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        String sql = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.printf("ID: %d | Name: %s | Dept: %s | Year: %d | Email: %s\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getInt("year"),
                        rs.getString("email"));
            } else {
                System.out.println("Student not found.");
            }
        }
    }

    private static void updateStudent() throws SQLException {
        System.out.print("Enter student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("New name: ");
        String name = scanner.nextLine();
        System.out.print("New department: ");
        String dept = scanner.nextLine();
        System.out.print("New year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("New email: ");
        String email = scanner.nextLine();

        String sql = "UPDATE students SET name=?, department=?, year=?, email=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, dept);
            stmt.setInt(3, year);
            stmt.setString(4, email);
            stmt.setInt(5, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student updated.");
            } else {
                System.out.println("Student not found.");
            }
        }
    }

    private static void deleteStudent() throws SQLException {
        System.out.print("Enter student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Student deleted.");
            } else {
                System.out.println("Student not found.");
            }
        }
    }
}
