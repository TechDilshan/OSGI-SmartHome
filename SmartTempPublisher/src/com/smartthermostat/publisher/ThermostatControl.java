package com.smartthermostat.publisher;

import java.util.List;

public interface ThermostatControl {
	void turnOnThermostat(String room);
    void turnOffThermostat(String room);
    void setTemperature(String room, double temperature);
    void displayAllThermostats();
    void addRoom(String room);
    void deleteRoom(String room);
    String getThermostatStatus(String room);
    List<String> getAllRooms();
}
