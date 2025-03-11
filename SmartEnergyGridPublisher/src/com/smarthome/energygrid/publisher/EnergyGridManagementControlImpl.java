package com.smarthome.energygrid.publisher;

public class EnergyGridManagementControlImpl implements EnergyGridManagementControl{
	
	private SolarEnergyProducer solarProducer;
    private BatteryStorage batteryStorage;
    private GridEnergyUsage gridEnergy;
    
    public EnergyGridManagementControlImpl() {
    	this.solarProducer = new SolarEnergyProducer();
	    this.batteryStorage = new BatteryStorage(10.0); // 10 kWh battery
	    this.gridEnergy = new GridEnergyUsage();
    }

	@Override
	public void generateSolarEnergy() {
		solarProducer.generateEnergy();
	}

	@Override
	public void chargeBatteryPower(double energy) {
		batteryStorage.chargeBattery(energy);
		
	}

	@Override
	public void useBatteryPower(double energy) {
		batteryStorage.useBattery(energy);
		
	}

	@Override
	public void importFromGridEnergy(double energy) {
		gridEnergy.importFromGrid(energy);
		
	}

	@Override
	public void exportToGridEnergy(double energy) {
		gridEnergy.exportToGrid(energy);
		
	}

	@Override
	public void displayEnergyDetails() {
        System.out.println("\nEnergy Grid System Overview:");
        System.out.println("Solar Energy Generated: " + solarProducer.getTotalGeneratedEnergy() + " kWh");
        System.out.println("Battery Charge: " + batteryStorage.getBatteryCharge() + " kWh");
        System.out.println("Imported Grid Energy: " + gridEnergy.getImportGridEnergy() + " kWh");
        System.out.println("Exported Grid Energy: " + gridEnergy.getExportGridEnergy() + " kWh");
    }

}
