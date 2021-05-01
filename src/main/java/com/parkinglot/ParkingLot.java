package main.java.com.parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public int MAX_SIZE = 0;
    public ArrayList<Integer> availableSlotList;
    Map<String, Car> slotToCarMapping;
    Map<String, String> registrationNumberToSlotMapping;
    Map<String, ArrayList<String>> driverAgeNumberToRegistrationNumberMapping;

    private static class Car {
        String regNo;
        String driverAge;
        public Car(String regNo, String driverAge) {
            this.regNo = regNo;
            this.driverAge = driverAge;
        }

        public String getRegNo() {
            return regNo;
        }

        public String getDriverAge() {
            return driverAge;
        }
    }

    public void createParkingLot(String lotCount) {
        this.MAX_SIZE = Integer.parseInt(lotCount);
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.MAX_SIZE; i++) {
            availableSlotList.add(i);
        }
        this.slotToCarMapping = new HashMap<>();
        this.registrationNumberToSlotMapping = new HashMap<>();
        this.driverAgeNumberToRegistrationNumberMapping = new HashMap<>();
        System.out.println("Created parking of " + lotCount + " slots");
    }
    public void park(String regNo, String driverAge) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.slotToCarMapping.size() == this.MAX_SIZE) {
            System.out.println("Sorry, parking lot is full");
        } else {
            Collections.sort(availableSlotList);
            String slot = availableSlotList.get(0).toString();
            Car car = new Car(regNo, driverAge);
            this.slotToCarMapping.put(slot, car);
            this.registrationNumberToSlotMapping.put(regNo, slot);
            ArrayList<String> regNoList;
            if (this.driverAgeNumberToRegistrationNumberMapping.containsKey(driverAge)) {
                regNoList = this.driverAgeNumberToRegistrationNumberMapping.get(driverAge);
                this.driverAgeNumberToRegistrationNumberMapping.remove(driverAge);
            } else {
                regNoList = new ArrayList<>();
            }
            regNoList.add(regNo);
            this.driverAgeNumberToRegistrationNumberMapping.put(driverAge, regNoList);
            System.out.println("Car with vehicle registration number \""+ regNo + "\" has been parked at slot number " + slot);
            availableSlotList.remove(0);
        }
    }
    public void leave(String slotNo) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.slotToCarMapping.size() > 0) {
            Car carToLeave = this.slotToCarMapping.get(slotNo);
            if (carToLeave != null) {
                this.slotToCarMapping.remove(slotNo);
                this.registrationNumberToSlotMapping.remove(carToLeave.regNo);
                ArrayList<String> regNoList = this.driverAgeNumberToRegistrationNumberMapping.get(carToLeave.driverAge);
                regNoList.remove(carToLeave.regNo);
                // Add the Lot No. back to available slot list.
                this.availableSlotList.add(Integer.parseInt(slotNo));
                System.out.println("Slot number " + slotNo +  " vacated, the car with vehicle registration number \"" + carToLeave.getRegNo() +
                        "\" left the space, the driver of the car was of age " + carToLeave.getDriverAge());
            } else {
                System.out.println("Slot number " + slotNo + " is already empty");
            }
        } else {
            System.out.println("Parking lot is empty");
        }
    }

    public void getRegistrationNumbersFromDriverAge(String driverAge) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.driverAgeNumberToRegistrationNumberMapping.containsKey(driverAge)) {
            ArrayList<String> regNoList = this.driverAgeNumberToRegistrationNumberMapping.get(driverAge);
            for (int i=0; i < regNoList.size(); i++) {
                if (!(i==regNoList.size() - 1)){
                    System.out.print(regNoList.get(i) + ",");
                } else {
                    System.out.print(regNoList.get(i));
                }
            }
        } else {
            System.out.println("No parked car matches the query");
        }
    }
    public void getSlotNumbersFromDriverAge(String driverAge) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else if (this.driverAgeNumberToRegistrationNumberMapping.containsKey(driverAge)) {
            ArrayList<String> regNoList = this.driverAgeNumberToRegistrationNumberMapping.get(driverAge);
            ArrayList<Integer> slotList = new ArrayList<>();
            for (String s : regNoList) {
                slotList.add(Integer.valueOf(this.registrationNumberToSlotMapping.get(s)));
            }
            Collections.sort(slotList);
            for (int j=0; j < slotList.size(); j++) {
                if (!(j == slotList.size() - 1)) {
                    System.out.print(slotList.get(j) + ",");
                } else {
                    System.out.print(slotList.get(j));
                }
            }
            System.out.println();
        } else {
            System.out.println("No parked car matches the query");
        }
    }
    public void getSlotNumberFromRegNo(String regNo) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
        } else
            System.out.println(this.registrationNumberToSlotMapping.getOrDefault(regNo, "No parked car matches the query"));
    }
}