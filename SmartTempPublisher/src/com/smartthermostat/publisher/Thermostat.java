package com.smartthermostat.publisher;

public class Thermostat {
	private double temperature;
    private boolean isOn;

    public Thermostat() {
        this.temperature = 22.0; // Default temperature in Celsius
        this.isOn = false;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public boolean isOn() {
        return isOn;
    }
}
