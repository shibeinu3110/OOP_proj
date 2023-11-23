package connectData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JBDCUtil {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        final String url = "jdbc:sqlite:C:\\Users\\DELL\\Documents\\GitHub\\OOP_proj\\dict.db";
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\DELL\\Documents\\GitHub\\OOP_proj\\dict.db");


        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return DriverManager.getConnection(url);
    }
    public static void closeConnection(Connection c) {
        if (c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
