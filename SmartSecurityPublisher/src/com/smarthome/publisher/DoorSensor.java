package com.smarthome.publisher;

public class DoorSensor {
    private boolean isDoorOpen;

    public DoorSensor() {
        this.isDoorOpen = false; // Initially, the door is closed
    }

    public void openDoor() {
        isDoorOpen = true;
    }

    public void closeDoor() {
        isDoorOpen = false;
    }

    public boolean isDoorOpen() {
        return isDoorOpen;
    }
}
