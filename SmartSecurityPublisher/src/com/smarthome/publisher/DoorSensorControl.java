package com.smarthome.publisher;

public interface DoorSensorControl {
    void openDoor(String door, boolean isAuthorized);
    void closeDoor(String door);
    String showDoorStatus(String door);
    void displayAllDoors();
}
