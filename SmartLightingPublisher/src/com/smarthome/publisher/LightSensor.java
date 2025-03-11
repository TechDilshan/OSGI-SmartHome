package com.smarthome.publisher;

public class LightSensor {
    private boolean isLightOn;

    public LightSensor() {
        this.isLightOn = false;
    }

    public void turnOn() {
        isLightOn = true;
    }

    public void turnOff() {
        isLightOn = false;
    }

    public boolean isLightOn() {
        return isLightOn;
    }
}
