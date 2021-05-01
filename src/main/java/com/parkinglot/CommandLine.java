package main.java.com.parkinglot;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CommandLine {
    public Map<String, Method> commandsMap;

    public CommandLine() {
        commandsMap = new HashMap<>();
        try {
            populateCommandsHashMap();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    private void populateCommandsHashMap() throws NoSuchMethodException {
        commandsMap.put("Create_parking_lot", ParkingLot.class.getMethod("createParkingLot", String.class));
        commandsMap.put("Park", ParkingLot.class.getMethod("park", String.class, String.class));
        commandsMap.put("Leave", ParkingLot.class.getMethod("leave", String.class));
        commandsMap.put("Slot_numbers_for_driver_of_age", ParkingLot.class.getMethod("getSlotNumbersFromDriverAge", String.class));
        commandsMap.put("Slot_number_for_car_with_number", ParkingLot.class.getMethod("getSlotNumberFromRegNo", String.class));
        commandsMap.put("Vehicle_registration_number_for_driver_of_age", ParkingLot.class.getMethod("getRegistrationNumbersFromDriverAge", String.class));
    }
}