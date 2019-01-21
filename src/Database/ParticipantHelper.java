package Database;

import Model.Participant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParticipantHelper extends ConnectionHelper {

    public List<Participant> query(String query) {
        List<Participant> retList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery(query);
            if (!set.first()) {
                return null;
            }
            while (!set.isAfterLast()) {
                Participant expense = new Participant(
                        set.getInt("pid"),
                        set.getString("pname"),
                        set.getString("pemail"),
                        set.getString("ppassword"),
                        set.getInt("event1"),
                        set.getInt("event2"),
                        set.getInt("event3"),
                        set.getInt("event4")
                );
                retList.add(expense);
                set.next();
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("expense query exception = " + e.toString());
            return null;
        }
        return retList;
    }

    public Participant login(String email, String password) {
        String query = "select * from participant where pemail = \"" + email + "\" AND ppassword = \"" + password + "\";";
        List<Participant> list = query(query);
        if (list != null && list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    public void setResult(int pid, int score) {
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery(
                    "select * from participant where pid=" + pid + ";"
            );
            set.first();
            set.updateInt("event1", score);
            set.updateRow();
            set.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("expense updation exception = " + e.toString());
        }
    }
}
