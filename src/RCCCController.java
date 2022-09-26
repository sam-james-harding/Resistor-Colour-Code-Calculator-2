import resistor.*;

public class RCCCController {
    public static final int INITIAL_NUM_BANDS = 4;
    private RCCCView view;

    private Resistor resistor;

    private Integer numBands;

    /**
     * Initialise the controller class.
     */
    public RCCCController() {
        initialiseView();
    }

    private void initialiseView() {
        view = new RCCCView();

        view.setActionOnNumBandsChanged(this::onNumBandsChange);
        view.setActionOnDataChanged(this::onDataChange);
    }

    private void onDataChange(ResistorData data) {
        try {
            switch (numBands) {
                case 4:
                    resistor = new FourBandResistor(
                            data.digit1(), data.digit2(), data.multiplier(), data.tolerance()
                    );
                    break;
                case 5:
                    resistor = new FiveBandResistor(
                            data.digit1(), data.digit2(), data.digit3(), data.multiplier(), data.tolerance()
                    );
                    break;
                case 6:
                    resistor = new SixBandResistor(
                            data.digit1(), data.digit2(), data.digit3(),
                            data.multiplier(), data.tolerance(), data.tempCoeff()
                    );
                    break;
            }
        }
        catch (InvalidColourException ignored) {
            // this exception cannot actually occur in this circumstance, so just squash it
        }

        view.renderResistor(resistor);
        setResistanceLabel();
    }

    private void updateResistor() {
        onDataChange(view.getResistorData());
    }

    private void onNumBandsChange(Integer numBands) {
        this.numBands = numBands;

        if (numBands == 4) {
            view.setDigit3Enabled(false);
            view.setTempCoeffEnabled(false);
        } else if (numBands == 5) {
            view.setDigit3Enabled(true);
            view.setTempCoeffEnabled(false);
        } else {
            view.setDigit3Enabled(true);
            view.setTempCoeffEnabled(true);
        }

        updateResistor();
    }

    private void setResistanceLabel() {
        double resistance = resistor.getResistance();
        double tolerance = resistor.getTolerance();

        String resistanceText;

        if (resistance >= 1000000000) {
            resistanceText = String.format("%.2f", resistance/1000000000) + " GΩ";
        } else if (resistance >= 1000000) {
            resistanceText = String.format("%.2f", resistance/1000000) + " MΩ";
        } else if (resistance >= 1000) {
            resistanceText = String.format("%.2f", resistance/1000) + " KΩ";
        } else {
            resistanceText = String.format("%.2f", resistance) + " Ω";
        }

        resistanceText += String.format(" ± %.2f%%", tolerance);

        if (resistor.getTemperatureCoefficient().isPresent()) {
            resistanceText += String.format(
                    " (%d ppm/K)",
                    resistor.getTemperatureCoefficient().get()
            );
        }

        view.setResistanceLabelText(resistanceText);
    }

    /**
     * Start the app, including starting the GUI.
     */
    public void run() {
        view.run();
        onNumBandsChange(INITIAL_NUM_BANDS);
    }
}
