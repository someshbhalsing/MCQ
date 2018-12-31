package View;

import javax.swing.*;
import java.awt.*;

public class TestActivity {

    private static TestActivity ourInstance = new TestActivity();
    private String title;
    private JFrame frame;
    private TimerPanel mTimerPanel;
    private QuestionPanel mQuestionPanel;
    private ControllerPanel mControllerPanel;

    private TestActivity() {
        frame = new JFrame();
        frame.setBounds(0, 0, 1366, 768);
        frame.setLayout(new BorderLayout());
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
    }

    public void displayTimer() {
        mTimerPanel = new TimerPanel(30, new TimerCallbackEvents() {
            @Override
            public void timeFinished() {
                JOptionPane.showMessageDialog(frame.getParent(), "Test has ended");
            }
        });
        frame.getContentPane().add(mTimerPanel, BorderLayout.PAGE_START);
        mTimerPanel.start();
        frame.pack();
        frame.setVisible(true);
    }

}
