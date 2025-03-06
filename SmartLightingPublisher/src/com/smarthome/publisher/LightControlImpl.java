package com.smarthome.publisher;

import java.util.HashMap;
import java.util.Map;

public class LightControlImpl implements SmartLightingControl {

    private Map<String, Boolean> lightStatus = new HashMap<>();
    private Map<String, Integer> brightnessLevel = new HashMap<>();

    @Override
    public void turnOnLight(String room) {
        lightStatus.put(room, true);
        brightnessLevel.put(room, 100);  // Default to full brightness
        System.out.println("Light in " + room + " turned ON.");
    }

    @Override
    public void turnOffLight(String room) {
        lightStatus.put(room, false);
        brightnessLevel.put(room, 0);  // Light turned off
        System.out.println("Light in " + room + " turned OFF.");
    }

    @Override
    public void adjustBrightness(String room, int level) {
        brightnessLevel.put(room, level);
        System.out.println("Brightness in " + room + " set to " + level + "%.");
    }

    @Override
    public void showLightStatus(String room) {
        if (lightStatus.getOrDefault(room, false)) {
            System.out.println("Light in " + room + " is ON with brightness: " + brightnessLevel.get(room) + "%.");
        } else {
            System.out.println("Light in " + room + " is OFF.");
        }
    }
}
