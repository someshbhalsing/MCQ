package Database;

import Model.Participant;

import java.sql.*;

public class LocalConnectionHelper {
    static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    static final String USER_NAME = "root";
    static final String PASSWORD = "admin@123";
    private static final String SERVER_LINK = "jdbc:mysql://localhost/";
    private static final String DATABASE_NAME = "m-pulse";
    static final String DB_SERVER_URL = SERVER_LINK + DATABASE_NAME;
    static Connection connection;

    LocalConnectionHelper() {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception");
        }
        try {
            if (connection == null || connection.isClosed()) {
                String abc = "jdbc:mysql://" + DB_SERVER_URL + ":3306/" + DATABASE_NAME;
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

    public void insertParticipant(Participant participant) {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery("select * from participant");
            set.moveToInsertRow();
            set.updateString("pname", participant.getName());
            set.updateString("pemail", participant.getEmail());
            set.updateString("ppassword", participant.getPassword());
            set.updateInt("event1", -1);
            set.updateInt("event2", -2);
            set.updateInt("event3", -2);
            set.updateInt("event4", -2);
            set.updateInt("event5", -2);
            set.insertRow();
            set.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("expense insertion exception = " + e.toString());
        }
    }

}
