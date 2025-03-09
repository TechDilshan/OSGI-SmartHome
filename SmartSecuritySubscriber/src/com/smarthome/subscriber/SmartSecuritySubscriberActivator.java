package com.smarthome.subscriber;

import com.smarthome.publisher.DoorSensorControl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SmartSecuritySubscriberActivator implements BundleActivator {

    private DoorSensorControl doorSensorControl;
    private Scanner input;
    private boolean isCameraOn = false;  
    private boolean isAlarmTriggered = false;  
    private int alarmSensitivity = 5;  

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("✅ Starting Smart Security Subscriber...");

        // Get the DoorSensorControl service from the publisher
        ServiceReference<DoorSensorControl> serviceReference = context.getServiceReference(DoorSensorControl.class);
        if (serviceReference != null) {
            doorSensorControl = context.getService(serviceReference);
        }

        if (doorSensorControl == null) {
            System.err.println("❌ Error: DoorSensorControl service not available!");
            return;
        }

        // Initialize scanner
        input = new Scanner(System.in);

        // Display the menu
        displayMenu();
    }

    private void displayMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n_________________________________________________________________________________");
            System.out.println("\n Welcome to the Smart Security System! 📢\n");
            System.out.println("1. Open Door");
            System.out.println("2. Close Door");
            System.out.println("3. Show Door Status");
            System.out.println("4. Display All Doors");
            System.out.println("5. Turn Off Camera");
            System.out.println("6. Trigger Alarm");
            System.out.println("7. Stop Alarm");
            System.out.println("8. Set Alarm Sensitivity");
            System.out.println("9. Show Camera Status");  
            System.out.println("10. Show Alarm Status");  
            System.out.println("11. Exit");
            System.out.print("\n🔹 Choose an option: ");

            try {
                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1 -> openDoor();
                    case 2 -> closeDoor();
                    case 3 -> showDoorStatus();
                    case 4 -> displayAllDoors();
                    case 5 -> turnOffCamera();
                    case 6 -> triggerAlarm();
                    case 7 -> stopAlarm();
                    case 8 -> setAlarmSensitivity();
                    case 9 -> showCameraStatus();  
                    case 10 -> showAlarmStatus();  
                    case 11 -> {
                        running = false;
                        System.out.println(" Exiting Smart Security System... Goodbye!");
                    }
                    default -> System.out.println(" Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println(" Invalid input. Please enter a number between 1-11.");
                input.nextLine(); 
            }
        }
    }

    private void openDoor() {
        System.out.print("Enter the door name to open: ");
        String door = input.nextLine();
        System.out.print("Is access authorized? (true/false): ");
        boolean isAuthorized = input.nextBoolean();
        input.nextLine();

        if (doorSensorControl != null) {
            if (isAuthorized) {
                doorSensorControl.openDoor(door, isAuthorized);
                System.out.println("✅ " + door + " is now OPEN.");
            } else {
                System.out.println("❌ Access denied for " + door + ". Unauthorized access attempt detected.");
                if (!isCameraOn) {
                    System.out.println("📷 Camera can be turned on when unauthorized access is detected.");
                    askToTurnOnCamera(); 
                } else {
                    System.out.println("📷 Camera is already on when unauthorized access detected.");
                }
            }
        } else {
            System.out.println("Error: Service unavailable.");
        }
    }

    private void askToTurnOnCamera() {
        System.out.print("Do you want to turn on the camera? (yes/no): ");
        String response = input.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            turnOnCamera();
        } else {
            System.out.println("Camera not turned on.");
        }
    }

    private void closeDoor() {
        System.out.print("Enter the door name to close: ");
        String door = input.nextLine();
        if (doorSensorControl != null) {
            doorSensorControl.closeDoor(door);
            System.out.println("✅ " + door + " is now CLOSED.");
        } else {
            System.out.println("Error: Service unavailable.");
        }
    }

    private void showDoorStatus() {
        System.out.print("Enter the door name to check status: ");
        String door = input.nextLine();
        if (doorSensorControl != null) {
            doorSensorControl.showDoorStatus(door);
        } else {
            System.out.println("Error: Service unavailable.");
        }
    }

    private void displayAllDoors() {
        if (doorSensorControl != null) {
            doorSensorControl.displayAllDoors();
        } else {
            System.out.println("Error: Service unavailable.");
        }
    }

    private void turnOffCamera() {
        System.out.println("📷 Turning off the camera...");
        isCameraOn = false;
    }

    private void triggerAlarm() {
        System.out.print("Enter door name for alarm: ");
        String doorName = input.nextLine();
        isAlarmTriggered = true;  // Set alarm as triggered
        System.out.println("⚠️ Alarm triggered for door: " + doorName);
    }

    private void stopAlarm() {
        System.out.println("🔕 Stopping the alarm...");
        isAlarmTriggered = false;  
    }

    private void setAlarmSensitivity() {
        System.out.print("Enter alarm sensitivity (1-10): ");
        int sensitivity = input.nextInt();
        input.nextLine(); 
        if (sensitivity >= 1 && sensitivity <= 10) {
            alarmSensitivity = sensitivity;
            System.out.println("✅ Alarm sensitivity set to: " + sensitivity);
        } else {
            System.out.println("❌ Invalid sensitivity level. Please enter a number between 1-10.");
        }
    }

    private void turnOnCamera() {
        System.out.println("📷 Turning on the camera...");
        isCameraOn = true;
        showCameraStatus();  
    }

    private void showCameraStatus() {
        System.out.println("\n<<<<<<<<<<<<< Camera Status >>>>>>>>>>>>>");
        System.out.println();
        System.out.println("||   Status   ||");
        System.out.println(" -------------");
        if (isCameraOn) {
            System.out.println("||    ON   ||");
        } else {
            System.out.println("||    OFF     ||");
        }
    }

    // Method to show alarm status in tabular format
    private void showAlarmStatus() {
        System.out.println("\n<<<<<<<<<<<<< Alarm Status >>>>>>>>>>>>>");
        System.out.println();
        System.out.println("||   Alarm Status   ||   Sensitivity   ||");
        System.out.println(" --------------------  ------------------");
        if (isAlarmTriggered) {
            System.out.println("||     Triggered     ||      " + alarmSensitivity + "        ||");
        } else {
            System.out.println("||     Not Triggered ||      " + alarmSensitivity + "        ||");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Smart Security Subscriber...");
        if (input != null) {
            input.close();
        }
    }
}
