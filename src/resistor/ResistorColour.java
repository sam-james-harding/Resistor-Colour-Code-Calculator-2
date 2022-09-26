package resistor;

import java.awt.Color;
import java.util.Optional;

/**
 * A colour for a band on a resistor.
 */
public enum ResistorColour {
    BLACK   (0,   0,   0,   0,    1.0,          null, 250),
    BROWN   (139, 69,  19,  1,    10.0,         1.0,  100),
    RED     (255, 0,   0,   2,    100.0,        2.0,  50),
    ORANGE  (255, 140, 0,   3,    1000.0,       3.0, 15),
    YELLOW  (255, 255, 0,   4,    10000.0,      4.0, 25),
    GREEN   (0,   255, 0,   5,    100000.0,     0.5,  20),
    BLUE    (0,   0,   255, 6,    1000000.0,    0.25, 10),
    VIOLET  (131, 0,   127, 7,    10000000.0,   0.1,  5),
    GREY    (105, 105, 105, 8,    100000000.0,  0.05, 1),
    WHITE   (255, 255, 255, 9,    1000000000.0, null, null),
    SILVER  (192, 192, 192, null, 0.01,         5.0, null),
    GOLD    (255, 215, 0,   null, 0.1,          10.0,  null);

    private final Color color;
    private final Integer digit;
    private final Double multiplier;
    private final Double tolerance;
    private final Integer tempCoeff;

    /**
     * Specifies a resistor band colour.
     * @param red the red component of the colour's RGB value
     * @param green the green component of the colour's RGB value
     * @param blue the blue component of the colour's RGB value
     * @param digit the colour's corresponding digit, if any (otherwise null)
     * @param multiplier the colour's corresponding multiplier, if any (otherwise null)
     * @param tolerance the colour's corresponding tolerance, if any (otherwise null)
     * @param tempCoeff the colour's corresponding temperature coefficient, if any (otherwise null)
     */
    ResistorColour(
            int red, int green, int blue, Integer digit, Double multiplier, Double tolerance, Integer tempCoeff
    ) {
        this.color = new Color(red, green, blue);

        this.digit = digit;
        this.multiplier = multiplier;
        this.tolerance = tolerance;
        this.tempCoeff = tempCoeff;
    }

    /**
     * Get this colour's RGB value.
     * @return This colour's RGB value (in the form of a java.awt Color)
     */
    public Color getColor() {
        return color;
    }

    /**
     * Get this colour's corresponding digit, if one exists.
     * @return this colour's corresponding digit, or any empty optional
     */
    public Optional<Integer> getDigit() {
        return Optional.ofNullable(digit);
    }

    /**
     * Get this colour's corresponding multiplier, if one exists.
     * @return this colour's corresponding multiplier, or any empty optional
     */
    public Optional<Double> getMultiplier() {
        return Optional.ofNullable(multiplier);
    }

    /**
     * Get this colour's corresponding tolerance, if one exists.
     * @return this colour's corresponding tolerance, or any empty optional
     */
    public Optional<Double> getTolerance() {
        return Optional.ofNullable(tolerance);
    }

    /**
     * Get this colour's corresponding temperature coefficient, if one exists.
     * @return this colour's corresponding temperature coefficient, or any empty optional
     */
    public Optional<Integer> getTempCoeff() {
        return Optional.ofNullable(tempCoeff);
    }

    @Override
    public String toString() {
        String uppercaseName = super.toString();
        return uppercaseName.charAt(0) + uppercaseName.substring(1).toLowerCase();
    }
}
