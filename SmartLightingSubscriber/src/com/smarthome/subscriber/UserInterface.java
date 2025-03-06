package com.smarthome.subscriber;

import com.smarthome.publisher.SmartLightingControl;

import java.util.Scanner;

public class UserInterface {

    private SmartLightingControl lightingControl;
    private Scanner input;

    public UserInterface(SmartLightingControl lightingControl, Scanner input) {
        this.lightingControl = lightingControl;
        this.input = input;
    }

    public void displayMenu() {
        System.out.println("Welcome to the Smart Lighting Control System!");
        int choice;
        do {
            System.out.println("1. Turn On Light");
            System.out.println("2. Turn Off Light");
            System.out.println("3. Adjust Brightness");
            System.out.println("4. Show Light Status");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter room name to turn on light: ");
                    String roomOn = input.next();
                    lightingControl.turnOnLight(roomOn);
                    break;
                case 2:
                    System.out.print("Enter room name to turn off light: ");
                    String roomOff = input.next();
                    lightingControl.turnOffLight(roomOff);
                    break;
                case 3:
                    System.out.print("Enter room name: ");
                    String roomBrightness = input.next();
                    System.out.print("Enter brightness (0-100): ");
                    int level = input.nextInt();
                    lightingControl.adjustBrightness(roomBrightness, level);
                    break;
                case 4:
                    System.out.print("Enter room name to check status: ");
                    String roomStatus = input.next();
                    lightingControl.showLightStatus(roomStatus);
                    break;
                case 5:
                    System.out.println("Exiting Smart Lighting Control System...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}
