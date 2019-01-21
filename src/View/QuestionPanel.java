package View;

import Model.Answer;
import Model.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class QuestionPanel extends JPanel implements ActionListener {

    private Question question;
    private ButtonGroup group;
    private int questionNo;
    private QuestionCallbackEvents mQuestionCallbackEvents;

    QuestionPanel(Question question, int questionNo) {
        this.question = question;
        this.questionNo = questionNo;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.white);

//        JLabel mQuestionLabel = new JLabel("<html>Q" + (questionNo + 1) + ". " + question.getMQuestion()+"</html>");
//        mQuestionLabel.validate();
//        mQuestionLabel.setOpaque(true);
//        mQuestionLabel.setFont(new Font("Calibri",Font.BOLD,22));
//        mQuestionLabel.setBackground(Color.white);
//        add(mQuestionLabel);

        JTextArea mQuestionLabel = new JTextArea("Q" + (questionNo + 1) + ". " + question.getMQuestion());
        mQuestionLabel.setEditable(false);
        mQuestionLabel.setLineWrap(true);
        mQuestionLabel.setWrapStyleWord(true);
        mQuestionLabel.setOpaque(true);
        mQuestionLabel.setFont(new Font("Calibri", Font.BOLD, 22));
        mQuestionLabel.setBackground(Color.white);

        JScrollPane pane = new JScrollPane(mQuestionLabel);
        pane.setBounds(0, 0, 795, 350);
        add(pane);

        List<Answer> choices = question.getMOptions();

        group = new ButtonGroup();
        JRadioButton[] options = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBackground(Color.white);
            options[i].setFont(new Font("Calibri", Font.PLAIN, 18));
            options[i].setText(choices.get(i).getMOption());
            options[i].addActionListener(this);
            group.add(options[i]);
            if (isAnswered()) {
                if (Objects.requireNonNull(question.getMMarkedAnswer()).getMId() == choices.get(i).getMId()) {
                    options[i].setSelected(true);
                }
            }
            add(options[i]);
        }

    }

    void setmQuestionCallbackEvents(QuestionCallbackEvents mQuestionCallbackEvents) {
        this.mQuestionCallbackEvents = mQuestionCallbackEvents;
    }

    private boolean isAnswered() {
        return question.getMStatus() == Question.ANSWERED;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    void update() {
        int selectedAnswer = getSelectedAnswer(getSelectedButtonText(group));
        if (selectedAnswer == -1)
            return;
        question.setMStatus(Question.ANSWERED);
        question.setMMarkedAnswer(fromOption(selectedAnswer));
        mQuestionCallbackEvents.update(questionNo, question);
    }

    private int getSelectedAnswer(String choiceText) {
        List<Answer> list = question.getMOptions();
        for (Answer ans : list) {
            if (ans.getMOption().equals(choiceText)) {
                return ans.getMId();
            }
        }
        return -1;
    }

    private String getSelectedButtonText(ButtonGroup grp) {
        for (Enumeration<AbstractButton> buttons = grp.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    private Answer fromOption(int option) {
        for (Answer answer : question.getMOptions()) {
            if (answer.getMId() == option)
                return answer;
        }
        return null;
    }
}
