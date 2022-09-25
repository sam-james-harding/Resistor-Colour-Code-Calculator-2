package resistor;

import java.util.List;
import java.util.Optional;

/**
 * A model for a resistor, with coloured bands, a resistance value,
 * a tolerance value, and possibly a temperature coefficient.
 */
public interface Resistor {
    /**
     * Get the colours of this resistor's bands, in order from left to right.
     * @return a list of this resistor's bands' colours
     */
    List<ResistorColour> getBandColours();

    /**
     * Get the number of bands on this resistor.
     * @return the number of bands on this resistor.
     */
    default int getNumberOfBands() {
        return getBandColours().size();
    }

    /**
     * Get this resistor's resistance.
     * @return this resistor's resistance
     */
    double getResistance();

    /**
     * Get this resistor's tolerance, in percent.
     * @return this resistor's tolerance
     */
    double getTolerance();

    /**
     * Get this resistor's temperature coefficient, in ppm/K, if it exists
     * @return this resistor's temperature coefficient, or an empty optional
     */
    Optional<Integer> getTemperatureCoefficient();
}
