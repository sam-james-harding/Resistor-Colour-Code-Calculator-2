package resistor;

import java.util.List;
import java.util.Optional;

public interface Resistor {
    List<ResistorColour> getBandColours();

    default int getNumberOfBands() {
        return getBandColours().size();
    }

    double getResistance();

    double getTolerance();

    Optional<Integer> getTemperatureCoefficient();
}
