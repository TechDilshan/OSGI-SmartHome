package com.smarthome.energy.subscriber;

import com.smarthome.energy.publisher.EnergyManagementControl;
import java.util.Scanner;

public class UserInterface {
	
	private EnergyManagementControl energyControl;
	private Scanner input;
	
	public UserInterface(EnergyManagementControl energyControl) {
		this.energyControl = energyControl;
		this.input = new Scanner(System.in);
	}
	
	public void start() {
		boolean running = true;
		
		while(running) {
			System.out.println("\n\n____________________________________________"
					+ "_________________________________________________________"
					+ "_________________________");
			System.out.println("\nWelcome to the Smart Home Energy Management System");
			System.out.println("1. Turn On Appliance");
			System.out.println("2. Turn Off Appliance");
			System.out.println("3. Set Working Time");
			System.out.println("4. Set Energy Consumed");
			System.out.println("5. Display Energy Consumption Details");
			System.out.println("6. Calculate Monthly Bill");
			System.out.println("7. Export Energy Consumption Data to File");
	        System.out.println("8. Exit");
			System.out.println("\n Choose an option: ");
			
			int choice = input.nextInt();
			input.nextLine();

			switch(choice) {
			  case 1: 
				turnOnAppliance();
				break;
			  case 2: 
				 turnOffAppliance();
				break;
			  case 3:
				 setWorkingTime();
				break;
			  case 4:
				  setEnergyConsumed();
				  break;
			  case 5:
				displayEnergyConsumptionDetails();
				break;
			  case 6:
			    calculateMonthlyBill();
			    break;
			  case 7:
				exportEnergyConsumptionData();
				break;
			  case 8:
				 running = false;
				 System.out.println("Exit Energy Management System.....");
				 break;
		      default:
		    	  System.out.println("Invalid Option. Please Try again");
			}
	      }
       }
	
	
	private void turnOnAppliance() {
		System.out.println("Enter the Appliance ID: ");
		String applianceId = input.nextLine();
		
		energyControl.turnOnAppliance(applianceId);
		
	}

	private void turnOffAppliance() {
		System.out.println("Enter the Appliance ID: ");
		String applianceId = input.nextLine();
		
		energyControl.turnOffAppliance(applianceId);
		
	}
	
	private void setWorkingTime() {
        System.out.print("Enter the appliance ID: ");
        String applianceId = input.nextLine();
        System.out.print("Enter the working time (in hours): ");
        double workingTime = input.nextDouble();
        input.nextLine();
        energyControl.setWorkingTime(applianceId, workingTime);
    }

    private void setEnergyConsumed() {
        System.out.print("Enter the appliance ID: ");
        String applianceId = input.nextLine();
        System.out.print("Enter the energy consumed (in kWh): ");
        double energyConsumed = input.nextDouble();
        input.nextLine();
        energyControl.setEnergyConsumed(applianceId, energyConsumed);
    }
    
	
	private void displayEnergyConsumptionDetails() {
		energyControl.displayEnergyConsumptionDetails();
		
	}
	
	private void calculateMonthlyBill() {
		System.out.println("Enter the Appliance ID: ");
		String applianceId = input.nextLine();
		
		double monthlyBill = energyControl.calculateMonthlyBill(applianceId);
		if(monthlyBill != -1) {
			System.out.println("Monthly Bill for "+applianceId + " :LKR"+ String.format("%.2f", monthlyBill));
		}
		
	}
	
	private void exportEnergyConsumptionData() {
	    System.out.print("Enter the filename to export data (e.g., energy_data.csv): ");
	    String filename = input.nextLine();

	    // Save the file in the user's directory
	    String path = System.getProperty("user.home") + "\\Downloads\\" + filename;

	    try {
	        energyControl.exportEnergyConsumptionDataToFile(path);
	        System.out.println("Data successfully exported to " + path);
	    } catch (Exception e) {
	        System.out.println("Error exporting data to file: " + e.getMessage());
	        System.out.println("Please check the file path and permissions.");
	    }
	}
}

	
	
	
