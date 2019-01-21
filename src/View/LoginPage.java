package View;

import Database.ParticipantHelper;
import Database.QuestionHelper;
import Model.Participant;
import Model.Question;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoginPage {
    private JFrame frame;

    public LoginPage() {
        initialize();
    }

    public static void main(String[] args) {

        new LoginPage();
    }

    void initialize() {
        frame = new JFrame();
        frame.setBounds(0, 0, 1366, 768);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        JLabel lblTitle = new JLabel("Login Page");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 28));
        lblTitle.setBounds(650, 200, 300, 50);
        frame.getContentPane().add(lblTitle);

        JLabel lblusername = new JLabel("Username :");
        lblusername.setFont(new Font("Dialog", Font.PLAIN, 18));
        lblusername.setBounds(550, 300, 100, 50);
        frame.getContentPane().add(lblusername);

        JLabel lblpassword = new JLabel("Password :");
        lblpassword.setFont(new Font("Dialog", Font.PLAIN, 18));
        lblpassword.setBounds(550, 400, 100, 50);
        frame.getContentPane().add(lblpassword);

        JTextField textUsername = new JTextField();
        textUsername.setFont(new Font("Dialog", Font.PLAIN, 18));
        textUsername.setBounds(680, 300, 200, 50);
        frame.getContentPane().add(textUsername);

        JTextField textPassword = new JTextField();
        textPassword.setFont(new Font("Dialog", Font.PLAIN, 18));
        textPassword.setBounds(680, 400, 200, 50);
        frame.getContentPane().add(textPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(SystemColor.controlHighlight);
        btnLogin.setFont(new Font("Dialog", Font.PLAIN, 22));
        btnLogin.setBounds(640, 550, 160, 60);

        btnLogin.addActionListener(e -> {
            ParticipantHelper helper = new ParticipantHelper();
            String email = textUsername.getText();
            String password = textPassword.getText();
            if (email == null || password == null) {
                JOptionPane.showMessageDialog(frame.getParent(), "Mandatory field");
                return;
            }
            Participant p = helper.login(email, password);
            if (p == null) {
                JOptionPane.showMessageDialog(frame.getParent(), "Incorrect details");
                return;
            }
            if (p.getWebberStatus() == -1) {
                frame.dispose();
                TestActivity activity = TestActivity.getInstance();
                activity.setParticipant(p);
                activity.setTestTimeInMinutes(15);
                List<Question> list = new QuestionHelper().getRandomList();
                activity.setQuestionList(list);
                activity.setmTestCallbackEvents(new TestCallbackEvents() {
                    @Override
                    public void onTestFinished(int finalScore) {
                        helper.setResult(p.getId(), finalScore);
                    }
                });
                activity.startTest();
            } else if (p.getWebberStatus() == -2) {
                JOptionPane.showMessageDialog(frame.getParent(), "You are not registered for this event");
                textUsername.setText("");
                textPassword.setText("");
                return;
            } else {
                JOptionPane.showMessageDialog(frame.getParent(), "You are exceeding the no. of attempts");
                textUsername.setText("");
                textPassword.setText("");
                return;
            }
        });

        frame.getContentPane().add(btnLogin);

        frame.setVisible(true);
    }

}