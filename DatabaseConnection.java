
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.*;


public class DatabaseConnection {

    private static final String username = "se370";
    private static final String password = "Team2!";
    private static final String database = "icook";
    private static final String url = "jdbc:mysql://izzy-se370.ca3u8x8hrfhy.us-west-1.rds.amazonaws.com:3306/" + database;

    public Statement statement;


    // Constructor
    DatabaseConnection() throws SQLException {
        // Register the Driver
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        // Getting the connection
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connection established....");

        // Creating a statement object
        statement = connection.createStatement();
    }


}
