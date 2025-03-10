package com.smarthome.energy.publisher;

public interface EnergyManagementControl {
	
	void turnOnAppliance(String applianceId);
	
	void turnOffAppliance(String applianceId);
	
	void setWorkingTime(String applianceId, double workingTime);
	
    void setEnergyConsumed(String applianceId, double energyConsumed);
	
	void displayEnergyConsumptionDetails();
	
	double calculateMonthlyBill(String applianceId, double ratePerkWh);
	
	void exportEnergyConsumptionDataToFile(String filename);
	

}
