package test.java.com.parkinglot;

import main.java.com.parkinglot.Input;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class InputTest {
    Input inputParser = new Input();
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
    public void parseTextInput() throws Exception {
        inputParser.parseTextInput("check");
        assertEquals("InvalidInput", outContent.toString().trim().replace(" ", ""));
        inputParser.parseTextInput("Leave 2");
        assertEquals("InvalidInput\r\nSorry,parkinglotisnotcreated", outContent.toString().trim().replace(" ", ""));
    }
}
