package com.smarthome.publisher;

public class LightSensor {

    public boolean isDaytime() {
        // Simulate checking if it's daytime (just a mock)
        return Math.random() > 0.5;
    }

    public void adjustLightBasedOnTime(SmartLightingControl controller, String room) {
        if (isDaytime()) {
            controller.adjustBrightness(room, 100);  // Full brightness during the day
        } else {
            controller.adjustBrightness(room, 30);   // Dim light at night
        }
    }
}
