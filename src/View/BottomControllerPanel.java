package View;

import javax.swing.*;
import java.awt.*;

class BottomControllerPanel extends JPanel {

    BottomControllerPanel(BottomControllerCallbackEvents events) {
        setLayout(new FlowLayout());
        JButton first = new JButton("First");
        first.addActionListener(e -> events.first());
        JButton previous = new JButton("Prev");
        previous.addActionListener(e -> events.prev());
        JButton next = new JButton("Next");
        next.addActionListener(e -> events.next());
        JButton last = new JButton("Last");
        last.addActionListener(e -> events.last());
        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> events.submit());

        add(first);
        add(previous);
        add(next);
        add(last);
        add(submit);
    }
}
