package Model;

import java.util.ArrayList;
import java.util.List;

public class QuestionTest {

    public static List<Question> questionList() {
        List<Question> list = new ArrayList<>();
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer(1, "Correct"));
        answerList.add(new Answer(2, "Wrong1"));
        answerList.add(new Answer(3, "Wrong2"));
        answerList.add(new Answer(4, "Wrong3"));
        list.add(new Question(1, "ABCD?", answerList, null));
        list.add(new Question(2, "QWER?", answerList, null));
        list.add(new Question(3, "TYUI?", answerList, null));
        list.add(new Question(4, "OPLK?", answerList, null));
        list.add(new Question(5, "JHGF?", answerList, null));
        list.add(new Question(6, "DSAZ?", answerList, null));
        list.add(new Question(7, "XCVB?", answerList, null));
        list.add(new Question(8, "NMMN?", answerList, null));
        list.add(new Question(9, "BVCX?", answerList, null));
        list.add(new Question(10, "ZASD?", answerList, null));
        return list;
    }


}
