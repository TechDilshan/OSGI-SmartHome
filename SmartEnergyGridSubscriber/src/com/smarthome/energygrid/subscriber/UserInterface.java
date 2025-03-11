package com.smarthome.energygrid.subscriber;

import java.util.Scanner;

import com.smarthome.energygrid.publisher.EnergyGridManagementControl;

public class UserInterface {
	
	private EnergyGridManagementControl energyControl;
    private Scanner input;

    public UserInterface(EnergyGridManagementControl energyControl) {
        this.energyControl = energyControl;
        this.input = new Scanner(System.in);
    }
    
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\nSmart Home Energy Grid System");
            System.out.println("1. Generate Solar Energy");
            System.out.println("2. Charge Battery");
            System.out.println("3. Use Battery");
            System.out.println("4. Import Energy from Grid");
            System.out.println("5. Export Energy to Grid");
            System.out.println("6. Display Energy Details");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                	generateSolarEnergy();
                    break;
                case 2:
                    chargeBattery();
                    break;
                case 3:
                    useBattery();
                    break;
                case 4:
                    importEnergy();
                    break;
                case 5:
                    exportEnergy();
                    break;
                case 6:
                    displayEnergyDetails();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting Energy Grid System...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    
    private void generateSolarEnergy() {
        energyControl.generateSolarEnergy();
    }

    private void chargeBattery() {
        System.out.print("Enter energy amount (kWh) to charge: ");
        double charge = input.nextDouble();
        energyControl.chargeBatteryPower(charge);
    }

    private void useBattery() {
        System.out.print("Enter energy amount (kWh) to use: ");
        double use = input.nextDouble();
        energyControl.useBatteryPower(use);
    }
    
    private void importEnergy() {
        System.out.print("Enter energy amount (kWh) to import: ");
        double importEnergy = input.nextDouble();
        energyControl.importFromGridEnergy(importEnergy);
    }

    private void exportEnergy() {
        System.out.print("Enter energy amount (kWh) to export: ");
        double exportEnergy = input.nextDouble();
        energyControl.exportToGridEnergy(exportEnergy);
    }

    private void displayEnergyDetails() {
        energyControl.displayEnergyDetails();
    }
    

}
