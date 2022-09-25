package resistor;

import java.util.List;
import java.util.Optional;

public class FourBandResistor implements Resistor {
    private final ResistorColour digit1, digit2, multiplier, tolerance;

    private final double resistanceValue;
    private final double toleranceValue;

    public FourBandResistor(
            ResistorColour digit1, ResistorColour digit2, ResistorColour multiplier, ResistorColour tolerance
    ) throws InvalidColourException {

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
