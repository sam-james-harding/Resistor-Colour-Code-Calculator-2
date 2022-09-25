import java.util.List;
import java.util.Optional;

public class SixBandResistor implements Resistor {
    private final ResistorColour digit1, digit2, digit3, multiplier, tolerance, tempCoeff;

    private final double resistanceValue;
    private final double toleranceValue;

    public SixBandResistor(
            ResistorColour digit1,
            ResistorColour digit2,
            ResistorColour digit3,
            ResistorColour multiplier,
            ResistorColour tolerance,
            ResistorColour tempCoeff
    ) throws InvalidColourException {

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
