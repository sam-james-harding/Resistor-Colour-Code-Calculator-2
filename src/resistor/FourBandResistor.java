package resistor;

import java.util.List;
import java.util.Optional;

public class FourBandResistor implements Resistor {
    private final ResistorColour digit1;
    private final ResistorColour digit2;
    private final ResistorColour multiplier;
    private final ResistorColour tolerance;

    private final double resistanceValue;
    private final double toleranceValue;

    /**
     * Create a new four-band resistor.
     * @param digit1 the first band of the resistor, specifying the first digit of the resistance
     * @param digit2 the second band of the resistor, specifying the second digit of the resistance
     * @param multiplier the third band of the resistor, specifying the multiplier of the resistance
     * @param tolerance the fourth band of the resistor, specifying the tolerance of the resistor
     * @throws InvalidColourException if a ResistorColour argument does not have the required data
     *      (i.e. digit1 does not have a digit value)
     */
    public FourBandResistor(
            ResistorColour digit1, ResistorColour digit2, ResistorColour multiplier, ResistorColour tolerance
    ) throws InvalidColourException {

        // if any colour band does not have the relevant values, the colour band is invalid
        if (    digit1.getDigit().isEmpty()
                || digit2.getDigit().isEmpty()
                || multiplier.getMultiplier().isEmpty()
                || tolerance.getTolerance().isEmpty()
        ) {
            throw new InvalidColourException();
        }

        this.digit1 = digit1;
        this.digit2 = digit2;
        this.multiplier = multiplier;
        this.tolerance = tolerance;

        resistanceValue = calculateResistance(
                digit1.getDigit().get(),
                digit2.getDigit().get(),
                multiplier.getMultiplier().get()
        );

        toleranceValue = tolerance.getTolerance().get();
    }

    /**
     * Calculate this resistor's resistance, from its band values
     * @param digit1 the first digit of the resistance
     * @param digit2 the second digit of the resistance
     * @param multiplier the third digit of the resistance
     * @return the resistor's resistance
     */
    private static double calculateResistance(int digit1, int digit2, double multiplier) {
        int baseResistance = (digit1 * 10) + digit2;
        return baseResistance * multiplier;
    }

    @Override
    public List<ResistorColour> getBandColours() {
        return List.of(digit1, digit2, multiplier, tolerance);
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
        return Optional.empty();
    }
}
