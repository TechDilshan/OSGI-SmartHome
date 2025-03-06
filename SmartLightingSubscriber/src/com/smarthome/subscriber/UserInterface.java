package com.smarthome.subscriber;

import com.smarthome.publisher.SmartLightingControl;

public class UserInterface {

    private SmartLightingControl smartLightingControl;

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
        System.out.println("6. Exit");
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
}
