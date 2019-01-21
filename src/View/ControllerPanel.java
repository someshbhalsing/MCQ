package View;

import Model.Question;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class ControllerPanel extends JPanel {


    private ControllerCallbackEvents mControllerCallbackEvents;
    private JButton buttons[];

    ControllerPanel(List<Question> mQuestionList) {

        buttons = new JButton[mQuestionList.size()];
        for (int i = 0; i < mQuestionList.size(); i++) {
            buttons[i] = new JButton("Question " + (i + 1));
            buttons[i].setForeground(Color.white);
            if (mQuestionList.get(i).getMStatus() == Question.ANSWERED) {
                buttons[i].setBackground(Color.green);
            } else {
                buttons[i].setBackground(Color.red);
            }
            int finalI = i;
            buttons[i].addActionListener(e -> mControllerCallbackEvents.onQuestionClicked(finalI));
            add(buttons[i]);
        }
    }

    void setmControllerCallbackEvents(ControllerCallbackEvents mControllerCallbackEvents) {
        this.mControllerCallbackEvents = mControllerCallbackEvents;
    }

    void update(List<Question> mQuestionList) {

        for (int i = 0; i < mQuestionList.size(); i++) {
            if (mQuestionList.get(i).getMStatus() == Question.ANSWERED) {
                buttons[i].setBackground(Color.green);
            } else {
                buttons[i].setBackground(Color.red);
            }
        }

        updateUI();
    }
}