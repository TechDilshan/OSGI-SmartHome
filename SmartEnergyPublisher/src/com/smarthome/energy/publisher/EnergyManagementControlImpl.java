package com.smarthome.energy.publisher;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnergyManagementControlImpl implements EnergyManagementControl {
	
	private Map<String, Appliance> appliances = new HashMap<>();
	
	public EnergyManagementControlImpl() {
        appliances.put("lightBulb-1", new Appliance("lightBulb-1", 0.1)); // 100W bulb
        appliances.put("lightbulb-2", new Appliance("lightbulb-2", 0.06));  // 60W bulb
        appliances.put("AC-1", new Appliance("AC-1", 0.08));  // Air Conditioner
        appliances.put("kettle", new Appliance("kettle", 1.2)); // Kettle
        appliances.put("blender", new Appliance("blender", 0.4));  // Blender
        appliances.put("fridge", new Appliance("fridge", 0.4));  // Fridge
        
    }
	

	@Override
	public void turnOnAppliance(String applianceId) {
		Appliance appliance = appliances.get(applianceId);
		if(appliance != null) {
			appliance.turnOn();
		}else {
			System.out.println("Appliance "+ applianceId + " does not exist");
		}
		
	}

	@Override
	public void turnOffAppliance(String applianceId) {
		Appliance appliance = appliances.get(applianceId);
		if(appliance != null) {
			appliance.turnOff();
		}else {
			System.out.println("Appliance "+ applianceId + " does not exist");
		}
		
	}
	
	@Override
	public void setWorkingTime(String applianceId, double workingTime) {
		if (workingTime < 0) {
			 System.out.println("Invalid working time. Working time cannot be negative ");
        }else {
        	Appliance appliance = appliances.get(applianceId);
            if (appliance != null) {
                appliance.setWorkingTime(workingTime);
                System.out.println("Working time for " + applianceId + " set to " + workingTime + " hours.");
            } else {
                System.out.println("Appliance " + applianceId + " does not exist.");
            }
        }
		
    }
		
	

	@Override
	public void setEnergyConsumed(String applianceId, double energyConsumed) {
		if (energyConsumed < 0) {
			 System.out.println("Invalid energy consumed. Energy Consumed cannot be negative ");
       }else {
    	   Appliance appliance = appliances.get(applianceId);
           if (appliance != null) {
        	   if (energyConsumed > appliance.getEnergyLimit()) {
                   System.out.println("Warning: Energy consumed by " + applianceId + " exceeds the energy limit. Turning off the appliance.");
                   appliance.turnOff(); 
        	   }else {
        		   appliance.setEnergyConsumed(energyConsumed);
                   System.out.println("Energy consumed by " + applianceId + " set to " + energyConsumed + " kWh.");
        	   }
           } else {
               System.out.println("Appliance " + applianceId + " does not exist.");
           }
       }
		
    }
		
	


	@Override
	public void displayEnergyConsumptionDetails() {
		System.out.println("\n\n###################################################################################################################################################\n");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-20s | %-15s | %-15s | %-15s | %-15s |\n",
        		"Appliance", "Turned ON", "Turned OFF", "Working Time(h)", "Energy(kWh)", "Energy Limit(kWh)", "Monthly Bill(LKR)" );
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        double ratePerkWh = 7.85;
        for(Appliance appliance : appliances.values()) {
        	String turnOn = appliance.getStartTime();
        	String turnOff = appliance.getEndTime();
        	double workingTime = appliance.getWorkingTime();
        	double energyConsumed = appliance.getEnergConsumed();
        	double energyLimit = appliance.getEnergyLimit();
        	double monthlyBill = energyConsumed * ratePerkWh * workingTime * 30;
        	System.out.printf("| %-10s | %-20s | %-20s | %-15.2f | %-15.2f | %-15.2f | %-15.2f |\n",
        			appliance.getApplianceId(), turnOn, turnOff, workingTime, energyConsumed, energyLimit, monthlyBill);
        	
        	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        	
        }
        
       
		
	}

	@Override
	public double calculateMonthlyBill(String applianceId, double ratePerkWh) {
		Appliance appliance = appliances.get(applianceId);
		if(appliance != null) {
			return appliance.getEnergConsumed() * ratePerkWh * appliance.getWorkingTime() * 30;
		}else {
			System.out.println("Appliance "+ applianceId + " does not exist");
			return -1;
		}
		
	}
	
	@Override
    public void exportEnergyConsumptionDataToFile(String filename) {
		
		    
        try (FileWriter writer = new FileWriter(filename)) {
            // Write the header row
            writer.write("Appliance,Turned ON,Turned OFF,Working Time(h),Energy(kWh),Energy Limit(kWh),Monthly Bill(LKR)\n");

            double ratePerkWh = 7.85;
            for (Appliance appliance : appliances.values()) {
                String turnOn = appliance.getStartTime();
                String turnOff = appliance.getEndTime();
                double workingTime = appliance.getWorkingTime();
                double energyConsumed = appliance.getEnergConsumed();
                double energyLimit = appliance.getEnergyLimit();
                double monthlyBill = energyConsumed * ratePerkWh * workingTime * 30;

                // Write the data row
                writer.write(String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f\n",
                        appliance.getApplianceId(), turnOn, turnOff, workingTime, energyConsumed, energyLimit, monthlyBill));
            }

            System.out.println("Energy consumption data exported to " + filename);
        } catch (IOException e) {
            System.err.println("Error exporting data to file: " + e.getMessage());
        }
    }
	
	
	
	

}
