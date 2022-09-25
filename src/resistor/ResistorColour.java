package resistor;

import java.awt.Color;
import java.util.Optional;

public enum ResistorColour {
    BLACK   (0,   0,   0,   0,    1.0,        null, 250),
    BROWN   (139, 69,  19,  1,    10.0,       1.0,  100),
    RED     (255, 0,   0,   2,    100.0,      2.0,  50),
    ORANGE  (255, 140, 0,   3,    1000.0,     null, 15),
    YELLOW  (255, 255, 0,   4,    10000.0,    null, 25),
    GREEN   (0,   255, 0,   5,    100000.0,   0.5,  20),
    BLUE    (0,   0,   255, 6,    1000000.0,  0.25, 10),
    PINK    (255, 20,  147, 7,    10000000.0, 0.1,  5),
    GREY    (105, 105, 105, 8,    null,       0.05, 1),
    WHITE   (255, 255, 255, 9,    null,       null, null),
    SILVER  (192, 192, 192, null, 0.01,       10.0, null),
    GOLD    (255, 215, 0,   null, 0.1,        5.0,  null);

    private final Color color;
    private final Integer digit;
    private final Double multiplier;
    private final Double tolerance;
    private final Integer tempCoeff;

    ResistorColour(
            int red, int green, int blue, Integer digit, Double multiplier, Double tolerance, Integer tempCoeff
    ) {
        this.color = new Color(red, green, blue);

        this.digit = digit;
        this.multiplier = multiplier;
        this.tolerance = tolerance;
        this.tempCoeff = tempCoeff;
    }

    public Color getColor() {
        return color;
    }

    public Optional<Integer> getDigit() {
        return Optional.ofNullable(digit);
    }

    public Optional<Double> getMultiplier() {
        return Optional.ofNullable(multiplier);
    }

    public Optional<Double> getTolerance() {
        return Optional.ofNullable(tolerance);
    }

    public Optional<Integer> getTempCoeff() {
        return Optional.ofNullable(tempCoeff);
    }
}
