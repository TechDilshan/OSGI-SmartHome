package com.smarthome.subscriber;

import com.smarthome.publisher.SmartLightingControl;
import java.util.Scanner;

public class UserInterface {

    private SmartLightingControl smartLightingControl;
    private Scanner input = new Scanner(System.in);

    public UserInterface(SmartLightingControl smartLightingControl) {
        this.smartLightingControl = smartLightingControl;
    }

    public void showMenu() {
        System.out.println("Welcome to the Smart Lighting Control System!");
        System.out.println("1. Turn On Light");
        System.out.println("2. Turn Off Light");
        System.out.println("3. Adjust Brightness");
        System.out.println("4. Show Light Status");
        System.out.println("5. Display All Lights");
        System.out.println("6. Add Room");
        System.out.println("7. Remove Room");
        System.out.println("8. Exit");
    }

    public void turnOnLight(String room) {
        smartLightingControl.turnOnLight(room);
    }

    public void turnOffLight(String room) {
        smartLightingControl.turnOffLight(room);
    }

    public void adjustBrightness(String room, int level) {
        smartLightingControl.adjustBrightness(room, level);
    }

    public void showLightStatus(String room) {
        smartLightingControl.showLightStatus(room);
    }

    public void displayAllLights() {
        smartLightingControl.displayAllLights();
    }

    // Add Room
    public void addRoom() {
        System.out.print("Enter the room name to add: ");
        String room = input.nextLine();
        smartLightingControl.addRoom(room);
    }

    // Remove Room
    public void removeRoom() {
        System.out.print("Enter the room name to remove: ");
        String room = input.nextLine();
        smartLightingControl.removeRoom(room);
    }
}
