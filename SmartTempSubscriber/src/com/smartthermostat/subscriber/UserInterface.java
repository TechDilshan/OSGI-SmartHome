package com.smartthermostat.subscriber;

import com.smartthermostat.publisher.ThermostatControl;

public class UserInterface {
	private ThermostatControl smartThermostatControl;

    public UserInterface(ThermostatControl smartThermostatControl) {
        this.smartThermostatControl = smartThermostatControl;
    }

    public void turnOnThermostat(String room) {
        smartThermostatControl.turnOnThermostat(room);
    }

    public void turnOffThermostat(String room) {
        smartThermostatControl.turnOffThermostat(room);
    }

    public void setTemperature(String room, double temperature) {
        smartThermostatControl.setTemperature(room, temperature);
    }

    public void getThermostatStatus(String room) {
        System.out.println(smartThermostatControl.getThermostatStatus(room));
    }
    
    public void displayAllThermostats() {
        smartThermostatControl.displayAllThermostats();
    }
    
    public void addRoom(String room) {
        smartThermostatControl.addRoom(room);
    }

    public void deleteRoom(String room) {
        smartThermostatControl.deleteRoom(room);
    }
}
