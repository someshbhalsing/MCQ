package View;

import javax.swing.*;
import java.awt.*;

public class BottomControllerPanel extends JPanel {

    JButton first;
    JButton last;
    JButton next;
    JButton previous;
    BottomControllerCallbackEvents events;

    public BottomControllerPanel(BottomControllerCallbackEvents events) {
        this.events = events;
        setLayout(new FlowLayout());
        first = new JButton("First");
        first.addActionListener(e -> events.first());
        previous = new JButton("Prev");
        previous.addActionListener(e -> events.prev());
        next = new JButton("Next");
        next.addActionListener(e -> events.next());
        last = new JButton("Last");
        last.addActionListener(e -> events.last());

        add(first);
        add(previous);
        add(next);
        add(last);
    }
}
