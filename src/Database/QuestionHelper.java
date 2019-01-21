package Database;

import Model.Answer;
import Model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionHelper extends ConnectionHelper {

    public List<Question> getRandomList() {
        String query = "select * from questions order by RAND() LIMIT 30";
        List<Question> list = query(query);
        return list;
    }

    public List<Question> query(String query) {
        List<Question> retList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet set = statement.executeQuery(query);
            if (!set.first()) {
                return null;
            }
            while (!set.isAfterLast()) {
                Answer answer1 = new Answer(1, set.getString("option1"));
                Answer answer2 = new Answer(2, set.getString("option2"));
                Answer answer3 = new Answer(3, set.getString("option3"));
                Answer answer4 = new Answer(4, set.getString("option4"));
                List<Answer> list = new ArrayList<>();
                list.add(answer1);
                list.add(answer2);
                list.add(answer3);
                list.add(answer4);
                Question question = new Question(
                        set.getInt("qid"),
                        set.getString("question_text"),
                        list,
                        null
                );
                retList.add(question);
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

}
