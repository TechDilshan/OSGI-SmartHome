package com.smarthome.energygrid.publisher;

public class BatteryStorage {
	
	private double batteryCapacity;
    private double currentCharge;

    public BatteryStorage(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
        this.currentCharge = 0.0;
    }
    
    public void chargeBattery(double energy) {
        if (energy <= 0) {
            System.out.println("Error: Energy value cannot be 0 or negative.");
            return;
        }
        if (currentCharge + energy > batteryCapacity) {
            System.out.println("Battery full! Excess energy will be sent to the grid.");
            currentCharge = batteryCapacity;
        } else {
            currentCharge += energy;
            System.out.println("Battery charged with " + energy + " kWh. Current charge: " + currentCharge + " kWh");
        }
    }
    
    public void useBattery(double energy) {
        if (energy <= 0) {
            System.out.println("Error: Energy value cannot be 0 or negative.");
            return;
        }
        if (energy > currentCharge) {
            System.out.println("Not enough battery storage. Importing energy from the grid.");
            currentCharge = 0;
        } else {
            currentCharge -= energy;
            System.out.println("Battery used: " + energy + " kWh. Remaining charge: " + currentCharge + " kWh");
        }
    }

    public double getBatteryCharge() {
        return currentCharge;
    }

}
