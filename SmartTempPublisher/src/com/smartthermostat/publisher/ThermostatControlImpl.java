package com.smartthermostat.publisher;

import java.util.HashMap;
import java.util.Map;

public class ThermostatControlImpl implements ThermostatControl{
	private Map<String, Thermostat> thermostats = new HashMap<>();

    public ThermostatControlImpl() {
        thermostats.put("Living Room", new Thermostat());
        thermostats.put("Bedroom", new Thermostat());
        thermostats.put("Kitchen", new Thermostat());
    }

	@Override
	public void turnOnThermostat(String room) {
		Thermostat thermostat = thermostats.get(room);
        if (thermostat != null && !thermostat.isOn()) {
            thermostat.turnOn();
            System.out.println("Thermostat in " + room + " turned ON.");
        } else {
            System.out.println("Thermostat in " + room + " is already ON or doesn't exist.");
        }		
	}

	@Override
	public void turnOffThermostat(String room) {
		Thermostat thermostat = thermostats.get(room);
        if (thermostat != null && thermostat.isOn()) {
            thermostat.turnOff();
            System.out.println("Thermostat in " + room + " turned OFF.");
        } else {
            System.out.println("Thermostat in " + room + " is already OFF or doesn't exist.");
        }		
	}

	@Override
	public void setTemperature(String room, double temperature) {
		Thermostat thermostat = thermostats.get(room);
        if (thermostat != null) {
            thermostat.setTemperature(temperature);
            System.out.println("Temperature in " + room + " set to " + temperature + "°C.");
        } else {
            System.out.println("Room doesn't exist.");
        }		
	}

	@Override
	public String getThermostatStatus(String room) {
		Thermostat thermostat = thermostats.get(room);
        if (thermostat != null) {
            return "Thermostat in " + room + " is " + (thermostat.isOn() ? "ON" : "OFF") + " at " + thermostat.getTemperature() + "°C.";
        } else {
            return "Room doesn't exist.";
        }
	}

	@Override
	public void displayAllThermostats() {
		System.out.println("\n-----------------------------------------------");
	    System.out.println("| Room Name       | Status  | Temperature (°C) |");
	    System.out.println("-----------------------------------------------");

	    for (Map.Entry<String, Thermostat> entry : thermostats.entrySet()) {
	        String room = entry.getKey();
	        Thermostat thermostat = entry.getValue();
	        String status = thermostat.isOn() ? "ON " : "OFF";
	        double temp = thermostat.getTemperature();

	        System.out.printf("| %-15s | %-6s | %-15.1f |\n", room, status, temp);
	    }

	    System.out.println("-----------------------------------------------");		
	}

	@Override
	public void addRoom(String room) {
		if (thermostats.containsKey(room)) {
	        System.out.println("Room '" + room + "' already exists.");
	    } else {
	        thermostats.put(room, new Thermostat());
	        System.out.println("Room '" + room + "' added successfully with default temperature.");
	    }		
	}

	@Override
	public void deleteRoom(String room) {
		if (thermostats.containsKey(room)) {
	        thermostats.remove(room);
	        System.out.println("Room '" + room + "' has been removed.");
	    } else {
	        System.out.println("Room '" + room + "' does not exist.");
	    }		
	}
}
