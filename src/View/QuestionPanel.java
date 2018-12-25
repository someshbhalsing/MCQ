package View;

import Model.Question;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.util.List;

public class QuestionPanel extends JPanel {

    private List<Question> mQuestionList;

    public QuestionPanel() {
        throw new NotImplementedException();
    }

    public void setQuestionList(List<Question> mQuestionList){
        this.mQuestionList = mQuestionList;
    }


}