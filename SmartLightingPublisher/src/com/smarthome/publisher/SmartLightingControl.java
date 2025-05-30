package com.smarthome.publisher;

public interface SmartLightingControl {
    void turnOnLight(String room);
    void turnOffLight(String room);
    void adjustBrightness(String room, int level);
    String showLightStatus(String room);
    void displayAllLights();
    void addRoom(String room);
    void removeRoom(String room);
}
