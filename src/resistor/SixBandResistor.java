package resistor;

import java.util.List;
import java.util.Optional;

public class SixBandResistor implements Resistor {
    private final ResistorColour digit1;
    private final ResistorColour digit2;
    private final ResistorColour digit3;
    private final ResistorColour multiplier;
    private final ResistorColour tolerance;
    private final ResistorColour tempCoeff;

    private final double resistanceValue;
    private final double toleranceValue;

    /**
     * Create a new six-band resistor.
     * @param digit1 the first band of the resistor, specifying the first digit of the resistance
     * @param digit2 the second band of the resistor, specifying the second digit of the resistance
     * @param digit3 the third band of the resistor, specifying the third digit of the resistance
     * @param multiplier the fourth band of the resistor, specifying the multiplier of the resistance
     * @param tolerance the fifth band of the resistor, specifying the tolerance of the resistor
     * @param tempCoeff the sixth band of the resistor, specifying the temperature coefficient of the resistor
     * @throws InvalidColourException if a ResistorColour argument does not have the required data
     *      (i.e. digit1 does not have a digit value)
     */
    public SixBandResistor(
            ResistorColour digit1,
            ResistorColour digit2,
            ResistorColour digit3,
            ResistorColour multiplier,
            ResistorColour tolerance,
            ResistorColour tempCoeff
    ) throws InvalidColourException {

        // if any colour band does not have the relevant values, the colour band is invalid
        if (    digit1.getDigit().isEmpty()
                || digit2.getDigit().isEmpty()
                || digit3.getDigit().isEmpty()
                || multiplier.getMultiplier().isEmpty()
                || tolerance.getTolerance().isEmpty()
                || tempCoeff.getTempCoeff().isEmpty()
        ) {
            throw new InvalidColourException();
        }

        this.digit1 = digit1;
        this.digit2 = digit2;
        this.digit3 = digit3;
        this.multiplier = multiplier;
        this.tolerance = tolerance;
        this.tempCoeff = tempCoeff;

        resistanceValue = calculateResistance(
                digit1.getDigit().get(),
                digit2.getDigit().get(),
                digit3.getDigit().get(),
                multiplier.getMultiplier().get()
        );

        toleranceValue = tolerance.getTolerance().get();
    }

    /**
     * Calculate this resistor's resistance, from its band values
     * @param digit1 the first digit of the resistance
     * @param digit2 the second digit of the resistance
     * @param digit3 the third digit of the resistance
     * @param multiplier the third digit of the resistance
     * @return the resistor's resistance
     */
    private static double calculateResistance(int digit1, int digit2, int digit3, double multiplier) {
        int baseResistance = (digit1 * 100) + (digit2 * 10) + digit3;
        return baseResistance * multiplier;
    }

    @Override
    public List<ResistorColour> getBandColours() {
        return List.of(digit1, digit2, digit3, multiplier, tolerance, tempCoeff);
    }

    @Override
    public double getResistance() {
        return resistanceValue;
    }

    @Override
    public double getTolerance() {
        return toleranceValue;
    }

    @Override
    public Optional<Integer> getTemperatureCoefficient() {
        return tempCoeff.getTempCoeff();
    }
}
