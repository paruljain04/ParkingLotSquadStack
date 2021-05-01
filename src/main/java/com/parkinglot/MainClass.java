package main.java.com.parkinglot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {
    public static void main(String[] args) {
        Input inputParser = new Input();
        switch (args.length) {
            case 0:
                System.out.println("Please enter 'exit' to quit");
                System.out.println("Please enter command to run from command line.. ");
                for (;;) {
                    try {
                        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                        String inputString = bufferRead.readLine();
                        if (inputString.equalsIgnoreCase("exit")) {
                            break;
                        } else if (inputString.length() > 0) {
                            inputParser.parseTextInput(inputString.trim());
                        }
                    } catch(IOException e) {
                        System.out.println("Oops! Error in reading the input from console.");
                        e.printStackTrace();
                    }
                }
                break;
            case 1:
                inputParser.parseFileInput(args[0]);
                break;
            default:
                System.out.println("Invalid Input. Usage: java -jar <jar_file_path> <input_file_path>");
        }
    }
}