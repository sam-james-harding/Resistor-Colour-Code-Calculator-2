import resistor.Resistor;
import resistor.ResistorColour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.function.*;

public class RCCCView {
    private static final String TITLE = "Resistor Colour Code Calculator";
    public static final Dimension RESISTOR_CANVAS_MIN_SIZE = new Dimension(300, 300);

    private JPanel mainPanel;
    private JComboBox<Integer> numBandsComboBox;
    private JComboBox<ResistorColour> digit1ComboBox;
    private JComboBox<ResistorColour> digit2ComboBox;
    private JComboBox<ResistorColour> digit3ComboBox;
    private JComboBox<ResistorColour> multiplierComboBox;
    private JComboBox<ResistorColour> toleranceComboBox;
    private JComboBox<ResistorColour> tempCoeffComboBox;
    private JPanel resistorCanvasContainer;
    private final ResistorCanvas resistorCanvas;
    private JPanel optionsPanel;
    private JLabel resistanceLabel;

    private static final Integer[] numBandsOptions = {4, 5, 6};

    private static final ResistorColour[] digitOptions = {
            ResistorColour.BLACK, ResistorColour.BROWN, ResistorColour.RED,
            ResistorColour.ORANGE, ResistorColour.YELLOW, ResistorColour.GREEN,
            ResistorColour.BLUE, ResistorColour.VIOLET, ResistorColour.GREY, ResistorColour.WHITE
    };

    private static final ResistorColour[] multiplierOptions = {
            ResistorColour.BLACK, ResistorColour.BROWN, ResistorColour.RED,
            ResistorColour.ORANGE, ResistorColour.YELLOW, ResistorColour.GREEN,
            ResistorColour.BLUE, ResistorColour.VIOLET, ResistorColour.GREY,
            ResistorColour.WHITE, ResistorColour.GOLD, ResistorColour.SILVER
    };

    private static final ResistorColour[] toleranceOptions = {
            ResistorColour.BROWN, ResistorColour.RED, ResistorColour.ORANGE,
            ResistorColour.YELLOW, ResistorColour.GREEN, ResistorColour.BLUE,
            ResistorColour.VIOLET, ResistorColour.GREY, ResistorColour.GOLD, ResistorColour.SILVER
    };

    private static final ResistorColour[] tempCoeffOptions = {
            ResistorColour.BLACK, ResistorColour.BROWN, ResistorColour.RED,
            ResistorColour.ORANGE, ResistorColour.YELLOW, ResistorColour.GREEN,
            ResistorColour.BLUE, ResistorColour.VIOLET, ResistorColour.GREY
    };

    public RCCCView() {
        // set options for combo boxes
        numBandsComboBox.setModel(new DefaultComboBoxModel<>(numBandsOptions));
        digit1ComboBox.setModel(new DefaultComboBoxModel<>(digitOptions));
        digit2ComboBox.setModel(new DefaultComboBoxModel<>(digitOptions));
        digit3ComboBox.setModel(new DefaultComboBoxModel<>(digitOptions));
        multiplierComboBox.setModel(new DefaultComboBoxModel<>(multiplierOptions));
        toleranceComboBox.setModel(new DefaultComboBoxModel<>(toleranceOptions));
        tempCoeffComboBox.setModel(new DefaultComboBoxModel<>(tempCoeffOptions));

        // set default options for combo boxes
        digit1ComboBox.setSelectedItem(ResistorColour.BROWN);

        // create and add resistor canvas
        resistorCanvas = new ResistorCanvas();
        resistorCanvas.setMinimumSize(RESISTOR_CANVAS_MIN_SIZE);

        resistorCanvasContainer.setLayout(new BorderLayout());
        resistorCanvasContainer.add(resistorCanvas);
    }

    public void setDigit3Enabled(boolean enabled) {
        digit3ComboBox.setEnabled(enabled);
    }

    public void setTempCoeffEnabled(boolean enabled) {
        tempCoeffComboBox.setEnabled(enabled);
    }

    public void setActionOnNumBandsChanged(Consumer<Integer> callback) {
        numBandsComboBox.addActionListener(
                e -> callback.accept((Integer) numBandsComboBox.getSelectedItem())
        );
    }

    public void setActionOnDataChanged(Consumer<ResistorData> callback) {
        ActionListener listener = e -> callback.accept(getResistorData());

        digit1ComboBox.addActionListener(listener);
        digit2ComboBox.addActionListener(listener);
        digit3ComboBox.addActionListener(listener);
        multiplierComboBox.addActionListener(listener);
        toleranceComboBox.addActionListener(listener);
        tempCoeffComboBox.addActionListener(listener);
    }

    public ResistorData getResistorData() {
        return new ResistorData(
                (ResistorColour) digit1ComboBox.getSelectedItem(),
                (ResistorColour) digit2ComboBox.getSelectedItem(),
                (ResistorColour) digit3ComboBox.getSelectedItem(),
                (ResistorColour) multiplierComboBox.getSelectedItem(),
                (ResistorColour) toleranceComboBox.getSelectedItem(),
                (ResistorColour) tempCoeffComboBox.getSelectedItem()
        );
    }

    public void renderResistor(Resistor resistor) {
        resistorCanvas.renderResistor(resistor);
    }

    public void setResistanceLabelText(String text) {
        resistanceLabel.setText(text);
    }

    /**
     * Create and run the window containing this view.
     */
    public void run() {
        JFrame frame = new JFrame(TITLE);
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
