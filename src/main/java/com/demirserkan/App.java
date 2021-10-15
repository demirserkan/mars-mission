package com.demirserkan;

import lombok.extern.slf4j.Slf4j;
import com.demirserkan.models.Plateau;
import com.demirserkan.models.Rover;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class App {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        log.info("Please give the upper-right coordinates of the plateau: ");
        String plateauMaxXYLimits = scanner.nextLine();

        String[] maxXYLimits = plateauMaxXYLimits.split(" ");

        Plateau plateau = new Plateau();
        plateau.setMaxXLimit(Integer.parseInt(maxXYLimits[0]));
        plateau.setMaxYLimit(Integer.parseInt(maxXYLimits[1]));

        List<String> roversFinalStatus = new ArrayList<>();
        boolean isItOver = false;
        int roverCount = 0;

        while (!isItOver){
            Rover rover = new Rover();
            rover.setPlateau(plateau);

            roverCount++;

            log.info("Please give the {}. rover's position: ", roverCount);
            String roverPositionInput = scanner.nextLine();

            rover.sendPositionAndDirectionInfo(roverPositionInput.split(" "));

            log.info("Please give the instruction about {}. rover's moves : ", roverCount);
            String instructionsInput = scanner.nextLine();

            rover.sendInstructionsInfo(instructionsInput);

            log.info("{}. rover's final position is {} {} and its direction is {}", roverCount, rover.getPosition().getCoordinateX(), rover.getPosition().getCoordinateY(), rover.getDirection());
            roversFinalStatus.add(rover.getRoverStatus());

            log.info("----------------------------------------------------------------------------------");
            log.info("{}. rover successfully landed and moved. Do you want to continue to land another rover? (Y/N)", roverCount);
            String finished = scanner.nextLine();
            if (finished.equalsIgnoreCase("N")){
                isItOver = true;
            }
        }

        roversFinalStatus.forEach(log::info);

    }
}
