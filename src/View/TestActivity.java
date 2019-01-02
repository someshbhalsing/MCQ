package View;

import Model.QuestionTest;

import javax.swing.*;
import java.awt.*;

public class TestActivity {

    private static TestActivity ourInstance = new TestActivity();
    private JFrame frame;
    private TimerPanel mTimerPanel;
    private QuestionPanel mQuestionPanel;
    private ControllerPanel mControllerPanel;
    private BottomControllerPanel mBottomControllerPanel;

    private TestActivity() {
        frame = new JFrame();
        frame.setBounds(0, 0, 1366, 768);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
    }

    public static TestActivity getInstance() {
        return ourInstance;
    }

    public static void main(String[] args) {
        TestActivity activity = TestActivity.getInstance();
        activity.displayTimer();
        activity.displayQuestionPanel();
        activity.displayBottomController();
    }

    public void displayTimer() {
        mTimerPanel = new TimerPanel(1, () -> {
            mQuestionPanel.setVisible(false);
            mBottomControllerPanel.setVisible(false);
            mTimerPanel.setVisible(false);
            JOptionPane.showMessageDialog(frame.getParent(), "The test has ended");
        });
        mTimerPanel.setBounds(1366 - 300, 0, 295, 75);
        mTimerPanel.setBackground(Color.white);
        frame.getContentPane().add(mTimerPanel);
        mTimerPanel.start();

    }

    public void displayQuestionPanel() {
        mQuestionPanel = new QuestionPanel(QuestionTest.questionList());
        mQuestionPanel.setBounds(1366 - 800, 80, 795, 578);
        mQuestionPanel.setBackground(Color.white);
        frame.getContentPane().add(mQuestionPanel);

        frame.setVisible(true);
    }

    public void displayBottomController() {
        mBottomControllerPanel = new BottomControllerPanel(new BottomControllerCallbackEvents() {
            @Override
            public void first() {
                mQuestionPanel.seek(0);
            }

            @Override
            public void last() {
                mQuestionPanel.seek(QuestionTest.questionList().size() - 1);
            }

            @Override
            public void next() {
                mQuestionPanel.next();
            }

            @Override
            public void prev() {
                mQuestionPanel.prev();
            }
        });
        mBottomControllerPanel.setBounds(1366 - 800, 668, 795, 90);
        frame.getContentPane().add(mBottomControllerPanel);
        frame.setVisible(true);
    }

}
