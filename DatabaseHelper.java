import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    public static Connection connect() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/todo_db"; 
            String user = "root";  
            String password = "Ajay"; 

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed!");
        }
        return connection;
    }
}
