package com.smarthome.energygrid.publisher;

public interface EnergyGridManagementControl {
	
	void generateSolarEnergy();

    void chargeBatteryPower(double energy);
    
    void useBatteryPower(double energy);
    
    void importFromGridEnergy(double energy);
    
    void exportToGridEnergy(double energy);
    
    void displayEnergyDetails();

}
