package com.smartthermostat.publisher;

public interface ThermostatControl {
	void turnOnThermostat(String room);
    void turnOffThermostat(String room);
    void setTemperature(String room, double temperature);
    void displayAllThermostats();
    void addRoom(String room);
    void deleteRoom(String room);
    String getThermostatStatus(String room);
}
