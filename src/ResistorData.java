import resistor.ResistorColour;

public record ResistorData(
        ResistorColour digit1,
        ResistorColour digit2,
        ResistorColour digit3,
        ResistorColour multiplier,
        ResistorColour tolerance,
        ResistorColour tempCoeff
) {}