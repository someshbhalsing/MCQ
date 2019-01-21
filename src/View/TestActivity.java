package View;

import Model.Participant;
import Model.Question;
import Model.QuestionTest;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestActivity {

    private static TestActivity ourInstance = new TestActivity();
    private JFrame frame;
    private TimerPanel mTimerPanel;
    private QuestionPanel mQuestionPanel;
    private ControllerPanel mControllerPanel;
    private BottomControllerPanel mBottomControllerPanel;
    private List<Question> mQuestionList;
    private int currentQuestion = 0;
    private int testTimeInMinutes;
    private Participant mParticipant;
    private ProfilePanel mProfilePanel;
    private int testId;
    private TestCallbackEvents mTestCallbackEvents;

    private TestActivity() {
        frame = new JFrame();
        frame.setBounds(0, 0, 1366, 768);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
    }

    public static void main(String[] args) {
        TestActivity activity = TestActivity.getInstance();
        List<Question> list = QuestionTest.questionList();
        activity.setQuestionList(list);
        activity.setTestTimeInMinutes(1);
        activity.setParticipant(new Participant(0, "AbC", "j@g.co", "adminfakl", -1, -1, -1, -1));
        activity.startTest();
    }

    public void setmTestCallbackEvents(TestCallbackEvents mTestCallbackEvents) {
        this.mTestCallbackEvents = mTestCallbackEvents;
    }

    public static TestActivity getInstance() {
        return ourInstance;
    }

    public void setQuestionList(List<Question> mQuestionList) {
        this.mQuestionList = mQuestionList;
    }

    public void setParticipant(Participant mParticipant) {
        this.mParticipant = mParticipant;
    }

    public void startTest() {
        displayTimer();
        displayQuestion();
        displayController();
        displayBottomController();
        displayProfileSection();
    }

    private void displayProfileSection() {
        mProfilePanel = new ProfilePanel(mParticipant);
        mProfilePanel.setBounds(5, 5, 966, 75);
        mTimerPanel.setBackground(Color.white);
        frame.getContentPane().add(mProfilePanel);
    }

    private void displayController() {
        mControllerPanel = new ControllerPanel(mQuestionList);
        mControllerPanel.setmControllerCallbackEvents(finalI -> displayQuestion(finalI));
        mControllerPanel.setBounds(10, 80, 546, 588);
        mControllerPanel.setBackground(Color.white);
        frame.getContentPane().add(mControllerPanel);
    }

    private void displayTimer() {
        mTimerPanel = new TimerPanel(testTimeInMinutes, this::submitTest);
        mTimerPanel.setBounds(1366 - 300, 0, 295, 75);
        mTimerPanel.setBackground(Color.white);
        frame.getContentPane().add(mTimerPanel);
        mTimerPanel.start();

    }

    private int getFinalScore(List<Question> list) {
        int score = 0;
        for (Question question : list) {
            if (question.getMStatus() == Question.ANSWERED && question.getMMarkedAnswer().getMId() == 1) {
                score++;
            }
        }
        return score;
    }

    private void displayQuestion() {
        displayQuestion(0);
    }

    private void displayQuestion(int questionNo) {
        currentQuestion = questionNo;
        if (mQuestionPanel != null) {
            frame.getContentPane().remove(mQuestionPanel);
        }
        mQuestionPanel = new QuestionPanel(mQuestionList.get(questionNo), questionNo);
        mQuestionPanel.setmQuestionCallbackEvents(new QuestionCallbackEvents() {
            @Override
            public void update(int questionNo, Question update) {
                mQuestionList.set(questionNo, update);
                mControllerPanel.update(mQuestionList);
            }
        });
        mQuestionPanel.setBounds(1366 - 800, 80, 795, 588);
        mTimerPanel.setBackground(Color.white);
        frame.getContentPane().add(mQuestionPanel);
        mQuestionPanel.updateUI();
    }

    private void displayBottomController() {
        mBottomControllerPanel = new BottomControllerPanel(new BottomControllerCallbackEvents() {
            @Override
            public void first() {
                mQuestionPanel.update();
                currentQuestion = 0;
                displayQuestion();
            }

            @Override
            public void last() {
                mQuestionPanel.update();
                currentQuestion = mQuestionList.size() - 1;
                displayQuestion(currentQuestion);
            }

            @Override
            public void next() {
                if (currentQuestion == mQuestionList.size() - 1)
                    return;
                mQuestionPanel.update();
                currentQuestion++;
                displayQuestion(currentQuestion);
            }

            @Override
            public void prev() {
                if (currentQuestion == 0)
                    return;
                mQuestionPanel.update();
                currentQuestion--;
                displayQuestion(currentQuestion);
            }

            @Override
            public void submit() {
                submitTest();
            }
        });
        mBottomControllerPanel.setBounds(1366 - 800, 668, 795, 90);
        frame.getContentPane().add(mBottomControllerPanel);
        frame.setVisible(true);
    }

    public void setTestTimeInMinutes(int testTimeInMinutes) {
        this.testTimeInMinutes = testTimeInMinutes;
    }

    private void submitTest() {
        mBottomControllerPanel.setVisible(false);
        mTimerPanel.setVisible(false);
        mQuestionPanel.setVisible(false);
        mControllerPanel.setVisible(false);
        mProfilePanel.setVisible(false);
        JOptionPane.showMessageDialog(frame.getParent(), "The Score is : " + getFinalScore(mQuestionList));
        mTestCallbackEvents.onTestFinished(getFinalScore(mQuestionList));
        frame.dispose();
        new LoginPage();
    }
}
