import com.demirserkan.enums.Direction;
import com.demirserkan.exceptions.LandingException;
import com.demirserkan.exceptions.PlateauLimitsException;
import com.demirserkan.models.Plateau;
import com.demirserkan.models.Rover;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverTest {

    private Rover rover;

    @BeforeEach
    void setUp() {
        Plateau plateau = new Plateau(5, 5);
        rover = new Rover();
        rover.setPlateau(plateau);
    }

    @Test
    void should_firstRoverTakePositionAndDirectionInfoSuccessfully_and_setsItsNewPosition() {
        String[] positionAndDirectionInfo = {"1", "2", "N"};
        String instructions = "LMLMLMLMM";

        rover.sendPositionAndDirectionInfo(positionAndDirectionInfo);
        rover.sendInstructionsInfo(instructions);

        assertEquals(1, rover.getPosition().getCoordinateX());
        assertEquals(3, rover.getPosition().getCoordinateY());
        assertEquals(Direction.N, rover.getDirection());
    }

    @Test
    void should_secondRoverTakePositionAndDirectionInfoSuccessfully_and_setsItsNewPosition() {
        String[] positionAndDirectionInfo = {"3", "3", "E"};
        String instructions = "MMRMMRMRRM";

        rover.sendPositionAndDirectionInfo(positionAndDirectionInfo);
        rover.sendInstructionsInfo(instructions);

        assertEquals(5, rover.getPosition().getCoordinateX());
        assertEquals(1, rover.getPosition().getCoordinateY());
        assertEquals(Direction.E, rover.getDirection());
    }

    @Test
    void should_sendInstructionsInfo_throwException_when_plateauYLimitsExceeded() {
        String[] positionAndDirectionInfo = {"4", "5", "N"};
        String instructions = "LMLMLMLMM";

        rover.sendPositionAndDirectionInfo(positionAndDirectionInfo);

        assertThrows(PlateauLimitsException.class, () -> rover.sendInstructionsInfo(instructions));
    }

    @Test
    void should_sendInstructionsInfo_throwException_when_plateauXLimitsExceeded() {
        String[] positionAndDirectionInfo = {"3", "1", "E"};
        String instructions = "MMRMMRMRRM";

        rover.sendPositionAndDirectionInfo(positionAndDirectionInfo);

        assertThrows(PlateauLimitsException.class, () -> rover.sendInstructionsInfo(instructions));
    }

    @Test
    void should_throwException_when_instructionIsNotDefined() {
        String[] positionAndDirectionInfo = {"3", "3", "E"};
        String instructions = "MMRMMRMRMA";

        rover.sendPositionAndDirectionInfo(positionAndDirectionInfo);

        assertThrows(IllegalArgumentException.class, () -> rover.sendInstructionsInfo(instructions));
    }

    @Test
    void landingFailed_when_xCoordinateIsOutsideThePlateauLimits(){

        String[] positionAndDirectionInfo = {"6", "0", "E"};

        assertThrows(LandingException.class, () -> rover.sendPositionAndDirectionInfo(positionAndDirectionInfo));

    }

    @Test
    void landingFailed_when_yCoordinateIsOutsideThePlateauLimits(){

        String[] positionAndDirectionInfo = {"3", "-1", "E"};

        assertThrows(LandingException.class, () -> rover.sendPositionAndDirectionInfo(positionAndDirectionInfo));

    }

    @Test
    void landingFailed_when_directionIsUndefined(){

        String[] positionAndDirectionInfo = {"0", "0", "C"};

        assertThrows(LandingException.class, () -> rover.sendPositionAndDirectionInfo(positionAndDirectionInfo));

    }
}
