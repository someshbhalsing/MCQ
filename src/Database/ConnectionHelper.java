package Database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    static final String USER_NAME = "root";
    static final String PASSWORD = "admin@123";
    private static final String SERVER_LINK = "jdbc:mysql://localhost/";
    private static final String DATABASE_NAME = "m-pulse";
    static final String DB_SERVER_URL = SERVER_LINK + DATABASE_NAME;
    static Connection connection;

    ConnectionHelper() {
        String SERVER = null;
        File file = new File("ip.txt");
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception");
        }
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    SERVER = br.readLine();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String abc = "jdbc:mysql://" + SERVER + ":3306/" + DATABASE_NAME;
                System.out.println(abc);
                connection = DriverManager.getConnection(
                        abc,
                        USER_NAME,
                        PASSWORD
                );
            }
        } catch (SQLException e) {
            System.out.println("Unable to establish connection " + e.toString());
        }
    }
}