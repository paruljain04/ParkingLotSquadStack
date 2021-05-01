package test.java.com.parkinglot;

import main.java.com.parkinglot.CommandLine;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CommandLineTest {
    CommandLine commands = new CommandLine();
    @Test
    public void checkCommandInList() throws Exception {
        assertFalse(commands.commandsMap.isEmpty());
        assertTrue(commands.commandsMap.containsKey("Create_parking_lot"));
        assertFalse(commands.commandsMap.containsKey("mytestcommand"));
    }
}
