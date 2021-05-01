package main.java.com.parkinglot;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Input {
    CommandLine commands;
    static ParkingLot parkingLot;
    public Input() {
        commands = new CommandLine();
        parkingLot = new ParkingLot();
    }
    public void parseTextInput(String inputString) {
        String[] inputs = inputString.split(" ");
        switch (inputs.length) {
            case 2:
                try {
                    Method method = commands.commandsMap.get(inputs[0]);
                    if (method != null) {
                        method.invoke(parkingLot, inputs[1]);
                    } else {
                        System.out.println("Invalid Input");
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case 4:
                try {
                    Method method = commands.commandsMap.get(inputs[0]);
                    if (method != null) {
                        method.invoke(parkingLot, inputs[1], inputs[3]);
                    } else {
                        System.out.println("Invalid Input");
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Invalid Input");
        }
    }
    public void parseFileInput(String filePath) {
        File inputFile = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    parseTextInput(line.trim());
                }
            } catch (IOException ex) {
                System.out.println("Error in reading the input file.");
                ex.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the path specified.");
            e.printStackTrace();
        }
    }
}