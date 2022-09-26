import resistor.Resistor;

import javax.swing.*;
import java.awt.*;

public class ResistorCanvas extends JPanel {
    public static final Color RESISTOR_COLOUR = new Color(254, 236, 170);
    public static final Color WIRE_COLOUR = new Color(193, 193, 193);

    public static final int BAND_WIDTH = 15;
    public static final int RESISTOR_WIDTH = 200;
    public static final int RESISTOR_HEIGHT = 100;
    public static final int WIRE_HEIGHT = 20;
    private Resistor currentResistor;

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        graphics.clearRect(0, 0, this.getWidth(), this.getHeight());

        if (currentResistor == null) return;

        graphics.setColor(WIRE_COLOUR);

        graphics.fillRect(
                0, (this.getHeight() - WIRE_HEIGHT)/2, this.getWidth(), WIRE_HEIGHT
        );

        graphics.setColor(RESISTOR_COLOUR);

        int resistorTopLeftX = (this.getWidth() - RESISTOR_WIDTH)/2;
        int resistorTopLeftY = (this.getHeight() - RESISTOR_HEIGHT)/2;

        graphics.fillRect(
                resistorTopLeftX,
                resistorTopLeftY,
                RESISTOR_WIDTH,
                RESISTOR_HEIGHT
        );

        int numBands = currentResistor.getNumberOfBands();
        int gapWidth = (RESISTOR_WIDTH - (numBands * BAND_WIDTH))/(numBands + 1);

        for (int i = 0; i < numBands; i++) {
            int currentDistance = i*BAND_WIDTH + (i + 1)*gapWidth;

            graphics.setColor(
                    currentResistor.getBandColours().get(i).getColor()
            );

            graphics.fillRect(
                    resistorTopLeftX + currentDistance,
                    resistorTopLeftY,
                    BAND_WIDTH,
                    RESISTOR_HEIGHT
            );
        }
    }

    public void renderResistor(Resistor resistor) {
        currentResistor = resistor;
        this.repaint();
    }
}
