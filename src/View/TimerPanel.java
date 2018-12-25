package View;

import javax.swing.*;

public class TimerPanel extends JPanel {

    private TimerCallbackEvents mTimerCallbackEvents;
    private Timer mTimer;
    private int seconds;
    private JLabel timerLabel;

    public TimerPanel(int initialMinutes, TimerCallbackEvents mTimerCallbackEvents) {
        this.mTimerCallbackEvents = mTimerCallbackEvents;
        seconds = initialMinutes * 60;
        timerLabel = new JLabel(remainingTime());
        mTimer = new Timer(1000, e -> {
            seconds--;
            timerLabel.setText(remainingTime());
            if (seconds <= 0) {
                this.mTimerCallbackEvents.timeFinished();
            }
        });
        add(timerLabel);
    }

    public String remainingTime() {
        int minute = seconds / 60;
        int second = seconds % 60;
        return "" + minute + ":" + second;
    }

    public void start() {
        mTimer.start();
    }
}
