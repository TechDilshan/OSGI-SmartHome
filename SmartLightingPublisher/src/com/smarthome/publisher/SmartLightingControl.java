package com.smarthome.publisher;

public interface SmartLightingControl {
    void turnOnLight(String room);
    void turnOffLight(String room);
    void adjustBrightness(String room, int level);
    void showLightStatus(String room);
}
