package com.smarthomewaste.subscriber;

import com.smarthomewaste.publisher.SmartBinControl;
import java.util.Scanner;

public class UserInterface {

    private SmartBinControl binControl;
    private Scanner scanner;

    // Constructor to inject the SmartBinControl service
    public UserInterface(SmartBinControl binControl) {
        this.binControl = binControl;
        this.scanner = new Scanner(System.in);
    }
    
    public void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the Smart Waste Control System!");
            System.out.println("1. Enter Waste Type");
            System.out.println("2. Display All Bin Statuses");
            System.out.println("8. Exit");

            // Get user input for the menu option
            System.out.print("Please select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character after nextInt()

            switch (choice) {
                case 1:
                    // Handle "Enter Waste Type"
                    System.out.print("Enter Waste Type (e.g., Paper, Organic, Metal): ");
                    String wasteType = scanner.nextLine();

                    // Check if the bin for the given waste type exists
                    String binId = wasteType;
                    if (binControl.checkWasteType(binId).equals("Bin not found")) {
                        System.out.println("Bin for " + wasteType + " not found.");
                        break;
                    }

                    // If the bin exists, check if it's full or not
                    if (binControl.isBinFull(binId)) {
                        System.out.println("The " + wasteType + " bin is full.");
                    } else {
                        System.out.print("Enter the amount of waste to add: ");
                        int wasteAmount = scanner.nextInt();
                        scanner.nextLine();  // Consume newline character after nextInt()

                        // Calculate new bin level and display it
                        int newLevel = binControl.addWasteToBin(binId, wasteAmount);
                        System.out.println("Bin " + binId + " updated. New level: " + newLevel + "/" + binControl.getBinCapacity(binId));
                    }
                    break;
                    
                case 2:
                    // Handle "Display All Bin Statuses"
                    binControl.displayAllBinStatuses();
                    break;
                    
                case 8:
                    // Exit the application
                    System.out.println("Exiting the system. Goodbye!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
