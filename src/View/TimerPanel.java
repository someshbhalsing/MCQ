package View;

import javax.swing.*;
import java.awt.*;

public class TimerPanel extends JPanel {

    private TimerCallbackEvents mTimerCallbackEvents;
    private Timer mTimer;
    private int seconds;
    private JLabel timerLabel;

    public TimerPanel(int initialMinutes, TimerCallbackEvents mTimerCallbackEvents) {
        this.mTimerCallbackEvents = mTimerCallbackEvents;
        seconds = initialMinutes * 60;
        timerLabel = new JLabel(remainingTime());
        timerLabel.setFont(new Font("Serif", Font.PLAIN, 40));
        timerLabel.setOpaque(true);
        timerLabel.setForeground(Color.green);
        timerLabel.setBackground(Color.white);
        mTimer = new Timer(1000, e -> {
            seconds--;
            timerLabel.setText(remainingTime());
            if (seconds <= 0) {
                this.mTimerCallbackEvents.timeFinished();
                mTimer.stop();
            }
        });
        add(timerLabel);

    }

    private String remainingTime() {
        int minute = seconds / 60;
        int second = seconds % 60;
        String time = "";
        if (minute < 10) {
            time += "0" + minute;
        } else {
            time += "" + minute;
        }
        if (second < 10) {
            time += ":0" + second;
        } else {
            time += ":" + second;
        }
        return time;
    }

    public void start() {
        mTimer.start();
    }
}
