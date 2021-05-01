package test.java.com.parkinglot;

import main.java.com.parkinglot.ParkingLot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ParkingLotTest {
    ParkingLot parkingLot = new ParkingLot();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
    @Test
    public void createParkingLot() {
        parkingLot.createParkingLot("6");
        assertEquals(6, parkingLot.MAX_SIZE);
        assertEquals(6, parkingLot.availableSlotList.size());
        assertFalse("createdparkinglotof6slots".equalsIgnoreCase(outContent.toString().trim().replace(" ", "")));
    }

    @Test
    public void park() {
        parkingLot.park("KA-01-HH-1234", String.valueOf(21));
        parkingLot.park("KA-01-HH-9999", String.valueOf(21));
        assertEquals("Sorry,parkinglotisnotcreated\r\n" +
                "Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", String.valueOf(21));
        parkingLot.park("KA-01-HH-9999", String.valueOf(21));
        assertEquals(4, parkingLot.availableSlotList.size());
    }

    @Test
    public void leave() {
        parkingLot.leave("2");
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", String.valueOf(21));
        parkingLot.park("KA-01-HH-9999", String.valueOf(21));
        parkingLot.leave("4");

        assertEquals("Sorry,parkinglotisnotcreated\r\n" +
                        "Createdparkingof6slots\r\n" +
                        "Carwithvehicleregistrationnumber\"KA-01-HH-1234\"hasbeenparkedatslotnumber1\r\n" +
                        "Carwithvehicleregistrationnumber\"KA-01-HH-9999\"hasbeenparkedatslotnumber2\r\n" +
                "Slotnumber4isalreadyempty", outContent.toString().trim().replace(" ", ""));
    }

    @Test
    public void getRegistrationNumbersFromDriverAge() {
        parkingLot.getRegistrationNumbersFromDriverAge(String.valueOf(21));
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", String.valueOf(21));
        parkingLot.park("KA-01-HH-9999", String.valueOf(21));
        parkingLot.getRegistrationNumbersFromDriverAge(String.valueOf(21));
        assertEquals("Sorry,parkinglotisnotcreated\r\n" +
                "Createdparkingof6slots\r\n"+
                "Carwithvehicleregistrationnumber\"KA-01-HH-1234\"hasbeenparkedatslotnumber1\r\n"+
                "Carwithvehicleregistrationnumber\"KA-01-HH-9999\"hasbeenparkedatslotnumber2\r\n" +
                "KA-01-HH-1234,KA-01-HH-9999", outContent.toString().trim().replace(" ", ""));
    }

    @Test
    public void getSlotNumbersFromDriverAge() {
        parkingLot.getSlotNumbersFromDriverAge(String.valueOf(21));
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", String.valueOf(21));
        parkingLot.park("KA-01-HH-9999", String.valueOf(21));
        parkingLot.getSlotNumbersFromDriverAge(String.valueOf(21));
        assertEquals("Sorry,parkinglotisnotcreated\r\n" +
                "Createdparkingof6slots\r\n"+
                "Carwithvehicleregistrationnumber\"KA-01-HH-1234\"hasbeenparkedatslotnumber1\r\n"+
                "Carwithvehicleregistrationnumber\"KA-01-HH-9999\"hasbeenparkedatslotnumber2\r\n"+
                "1,2", outContent.toString().trim().replace(" ", ""));
        parkingLot.getSlotNumbersFromDriverAge(String.valueOf(33));
        assertEquals("Sorry,parkinglotisnotcreated\r\n" +
                "Createdparkingof6slots\r\n" +
                "Carwithvehicleregistrationnumber\"KA-01-HH-1234\"hasbeenparkedatslotnumber1\r\n" +
                "Carwithvehicleregistrationnumber\"KA-01-HH-9999\"hasbeenparkedatslotnumber2\r\n" +
                "1,2\r\n" +
                "Noparkedcarmatchesthequery", outContent.toString().trim().replace(" ", ""));
    }

    @Test
    public void getSlotNumberFromRegNo() {
        parkingLot.getSlotNumberFromRegNo(String.valueOf(21));
        assertEquals("Sorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
        parkingLot.createParkingLot("6");
        parkingLot.park("KA-01-HH-1234", String.valueOf(21));
        parkingLot.park("KA-01-HH-9999", String.valueOf(21));
        parkingLot.getSlotNumberFromRegNo("KA-01-HH-1234");
        assertEquals("Sorry,parkinglotisnotcreated\r\n" +
                "Createdparkingof6slots\r\n" +
                "Carwithvehicleregistrationnumber\"KA-01-HH-1234\"hasbeenparkedatslotnumber1\r\n" +
                "Carwithvehicleregistrationnumber\"KA-01-HH-9999\"hasbeenparkedatslotnumber2\r\n" +
                "1", outContent.toString().trim().replace(" ", ""));
        parkingLot.getSlotNumberFromRegNo("KA-01-HH-9999");
        assertEquals("Sorry,parkinglotisnotcreated\r\n" +
                "Createdparkingof6slots\r\n" +
                "Carwithvehicleregistrationnumber\"KA-01-HH-1234\"hasbeenparkedatslotnumber1\r\n" +
                    "Carwithvehicleregistrationnumber\"KA-01-HH-9999\"hasbeenparkedatslotnumber2\r\n" +
                "1\r\n" + "2", outContent.toString().trim().replace(" ", ""));
    }

}
