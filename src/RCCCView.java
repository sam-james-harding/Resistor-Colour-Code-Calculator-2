import javax.swing.*;

public class RCCCView {

    private static final String TITLE = "Resistor Colour Code Calculator";

    private JPanel mainPanel;

    public void run() {
        JFrame frame = new JFrame(TITLE);
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
