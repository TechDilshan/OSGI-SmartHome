package com.smarthome.energy.publisher;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EnergyManagementControlImpl implements EnergyManagementControl {
	
	private Map<String, Appliance> appliances = new HashMap<>();
	
	public EnergyManagementControlImpl() {
        appliances.put("AC-1", new Appliance("AC-1", 0.08));  // Air Conditioner
        appliances.put("kettle", new Appliance("kettle", 1.2)); // Kettle
        appliances.put("blender", new Appliance("blender", 0.4));  // Blender
        appliances.put("fridge", new Appliance("fridge", 0.4));  // fridge
        
    }
	
	@Override
	public void addAppliance(String applianceId, double energyLimit) {
		if (appliances.containsKey(applianceId)) {
			System.out.println("Appliance " + applianceId + " already exists.");
		} else {
			appliances.put(applianceId, new Appliance(applianceId, energyLimit));
			System.out.println("Appliance " + applianceId + " added successfully.");
		}
	}
	
	@Override
	public void removeAppliance(String applianceId) {
	    if (appliances.containsKey(applianceId)) {
	        appliances.remove(applianceId);
	        System.out.println("Appliance " + applianceId + " removed successfully.");
	    } else {
	        System.out.println("Appliance " + applianceId + " does not exist.");
	    }
	}
	
	@Override
	public void updateApplianceEnergyLimit(String applianceId, double energyLimit) {
	    Appliance appliance = appliances.get(applianceId);
	    if (appliance != null) {
	        appliance.setEnergyConsumed(energyLimit);
	        System.out.println("Updated energy limit for " + applianceId + " to " + energyLimit + " kWh.");
	    } else {
	        System.out.println("Appliance " + applianceId + " does not exist.");
	    }
	}
	

	@Override
	public void listAppliances() {
	    System.out.println("\nList of Appliances:");
	    if (appliances.isEmpty()) {
	        System.out.println("No appliances available.");
	    } else {
	        for (String id : appliances.keySet()) {
	            System.out.println("- " + id);
	        }
	    }
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
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-20s | %-20s | %-20s | %-15s | %-15s | %-15s | %-18s |\n",
        		"Appliance", "Turned ON", "Turned OFF", "Working Time(h)", "Energy(kWh)", "Energy Limit(kWh)", "Monthly Bill(LKR)" );
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        
        double ratePerkWh = 7.85;
        for(Appliance appliance : appliances.values()) {
        	String turnOn = appliance.getStartTime();
        	String turnOff = appliance.getEndTime();
        	double workingTime = appliance.getWorkingTime();
        	double energyConsumed = appliance.getEnergConsumed();
        	double energyLimit = appliance.getEnergyLimit();
        	double monthlyBill = energyConsumed * ratePerkWh * workingTime * 30;
        	System.out.printf("| %-20s | %-20s | %-20s | %-15.2f | %-15.2f | %-17s | %-18s |\n",
        			appliance.getApplianceId(), turnOn, turnOff, workingTime, energyConsumed, energyLimit, monthlyBill);
        	
        	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        	
        }
        
       
		
	}

	@Override
	public double calculateMonthlyBill(String applianceId) {
		double ratePerkWh = 7.85;
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
