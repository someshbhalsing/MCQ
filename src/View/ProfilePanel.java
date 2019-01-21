package View;

import Model.Participant;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private Participant mParticipant;

    public ProfilePanel(Participant mParticipant) {
        this.mParticipant = mParticipant;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel name = new JLabel("Name : " + mParticipant.getName());
        JLabel email = new JLabel("Email : " + mParticipant.getEmail());

        name.setFont(new Font("Calibri", Font.BOLD, 28));
        email.setFont(new Font("Calibri", Font.BOLD, 20));

        add(name);
        add(email);
    }
}
