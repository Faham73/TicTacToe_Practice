package TicTacToe;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseConnection {
    private static final String URL ="jdbc:mysql://localhost:3306/tictactoedb";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);  // returns a Connection object that represents the connection to the database.
        // This Connection object is used to execute SQL queries and interact with the database.
    }

}
