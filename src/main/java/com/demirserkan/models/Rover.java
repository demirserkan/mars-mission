package com.demirserkan.models;

import com.demirserkan.enums.Direction;
import com.demirserkan.exceptions.LandingException;
import com.demirserkan.exceptions.PlateauLimitsException;
import lombok.Data;

import java.util.Arrays;

@Data
public class Rover {

    private Plateau plateau;
    private Position position;
    private Direction direction;

    public void sendPositionAndDirectionInfo(String[] positionAndDirectionInfo) {
        int xCoordinate = Integer.parseInt(positionAndDirectionInfo[0]);
        int yCoordinate = Integer.parseInt(positionAndDirectionInfo[1]);
        String roversDirection = positionAndDirectionInfo[2];

        checkGivenPositionAndDirectionIsValid(xCoordinate, yCoordinate, roversDirection);

        Position roverPositionInfo = new Position();
        roverPositionInfo.setCoordinateX(xCoordinate);
        roverPositionInfo.setCoordinateY(yCoordinate);

        this.position = roverPositionInfo;
        this.direction = Direction.valueOf(roversDirection);
    }

    private void checkGivenPositionAndDirectionIsValid(int xCoordinate, int yCoordinate, String roversDirection) {
        if (xCoordinate < plateau.getMinXLimit() || xCoordinate > plateau.getMaxXLimit()) {
            throw new LandingException("X coordinate is outside the plateau limits. xCoordinate: " + xCoordinate);
        } else if (yCoordinate < plateau.getMinYLimit() || yCoordinate > plateau.getMaxYLimit()) {
            throw new LandingException("Y coordinate is outside the plateau limits. xCoordinate: " + yCoordinate);
        } else if (Arrays.stream(Direction.values()).noneMatch(dr -> roversDirection.equalsIgnoreCase(dr.toString()))) {
            throw new LandingException("Given direction is undefined. Direction: " + roversDirection);
        }
    }

    public void sendInstructionsInfo(String instructionsInfo) {
        char[] instructionsArr = instructionsInfo.toCharArray();

        for (char instruction : instructionsArr) {
            switch (instruction) {
                case 'L':
                    this.direction = Direction.valueOf(this.direction.getLeft());
                    break;
                case 'R':
                    this.direction = Direction.valueOf(this.direction.getRight());
                    break;
                case 'M':
                    this.setPosition(setNewPosition());
                    break;
                default:
                    throw new IllegalArgumentException("Given instruction code is undefined. Instruction code: " + instruction);
            }
        }
    }

    private Position setNewPosition() {

        switch (direction) {
            case E:
                if (position.getCoordinateX() + 1 <= plateau.getMaxXLimit()) {
                    position.setCoordinateX(position.getCoordinateX() + 1);
                } else {
                    throw new PlateauLimitsException("Direction: " + direction + " new X coordinate: " + (position.getCoordinateX() + 1));
                }
                break;
            case W:
                if (position.getCoordinateX() - 1 >= plateau.getMinXLimit()) {
                    position.setCoordinateX(position.getCoordinateX() - 1);
                } else {
                    throw new PlateauLimitsException("Direction: " + direction + " new X coordinate: " + (position.getCoordinateX() - 1));
                }
                break;
            case N:
                if (position.getCoordinateY() + 1 <= plateau.getMaxYLimit()) {
                    position.setCoordinateY(position.getCoordinateY() + 1);
                } else {
                    throw new PlateauLimitsException("Direction: " + direction + " new Y coordinate: " + (position.getCoordinateY() + 1));
                }
                break;
            case S:
                if (position.getCoordinateY() - 1 >= plateau.getMinYLimit()) {
                    position.setCoordinateY(position.getCoordinateY() - 1);
                } else {
                    throw new PlateauLimitsException("Direction: " + direction + " new Y coordinate: " + (position.getCoordinateY() - 1));
                }
                break;
            default:
                throw new IllegalArgumentException("Given direction is undefined");
        }
        return position;
    }

    public String getRoverStatus() {

        return position.getCoordinateX() + " " + position.getCoordinateY() + " " + direction.toString();
    }
}
