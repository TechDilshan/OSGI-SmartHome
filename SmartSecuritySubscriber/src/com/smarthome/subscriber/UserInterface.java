package com.smarthome.subscriber;

import com.smarthome.publisher.DoorSensorControl;
import com.smarthome.publisher.CameraControl;
import com.smarthome.publisher.AlarmControl;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {

    private DoorSensorControl doorSensorControl;
    private CameraControl cameraControl;
    private AlarmControl alarmControl;
    private Scanner scanner;

    public UserInterface(DoorSensorControl doorSensorControl, CameraControl cameraControl) {
        this.doorSensorControl = doorSensorControl;
        this.cameraControl = cameraControl;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Smart Home Control System ===");
            System.out.println("1. Open Door");
            System.out.println("2. Close Door");
            System.out.println("3. Show Door Status");
            System.out.println("4. Display All Doors");
            System.out.println("5. Turn Off Camera");
            System.out.println("6. Show Camera Status");
            System.out.println("7. Trigger Alarm");
            System.out.println("8. Stop Alarm");
            System.out.println("9. Set Alarm Sensitivity");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear the buffer
                continue;
            }

            switch (choice) {
                case 1:
                    openDoor();
                    break;
                case 2:
                    closeDoor();
                    break;
                case 3:
                    showDoorStatus();
                    break;
                case 4:
                    displayAllDoors();
                    break;
                case 5:
                    turnOnCamera();
                    break;
                case 6:
                    turnOffCamera();
                    break;
                case 7:
                    triggerAlarm();
                    break;
                case 8:
                    stopAlarm();
                    break;
                case 9:
                    setAlarmSensitivity();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void openDoor() {
        System.out.print("Enter door name: ");
        String doorName = scanner.nextLine();
        System.out.print("Is access authorized? (true/false): ");
        boolean isAuthorized = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline
        if (doorSensorControl != null) {
            doorSensorControl.openDoor(doorName, isAuthorized);
        } else {
            System.out.println("Error: Door sensor service unavailable.");
        }
    }

    private void closeDoor() {
        System.out.print("Enter door name: ");
        String doorName = scanner.nextLine();
        if (doorSensorControl != null) {
            doorSensorControl.closeDoor(doorName);
        } else {
            System.out.println("Error: Door sensor service unavailable.");
        }
    }

    private void showDoorStatus() {
        System.out.print("Enter door name: ");
        String doorName = scanner.nextLine();
        if (doorSensorControl != null) {
            doorSensorControl.showDoorStatus(doorName);
        } else {
            System.out.println("Error: Door sensor service unavailable.");
        }
    }

    private void displayAllDoors() {
        if (doorSensorControl != null) {
            doorSensorControl.displayAllDoors();
        } else {
            System.out.println("Error: Door sensor service unavailable.");
        }
    }

    private void turnOnCamera() {
        System.out.print("Enter door name for camera: ");
        String doorName = scanner.nextLine();
        if (cameraControl != null) {
            cameraControl.turnOnCamera(doorName);
        } else {
            System.out.println("Error: Camera service unavailable.");
        }
    }

    private void turnOffCamera() {
        if (cameraControl != null) {
            cameraControl.turnOffCamera();
        } else {
            System.out.println("Error: Camera service unavailable.");
        }
    }

    private void triggerAlarm() {
        System.out.print("Enter door name for alarm: ");
        String doorName = scanner.nextLine();
        if (alarmControl != null) {
            // Pass the doorName as a parameter to trigger the alarm
            alarmControl.triggerAlarm(doorName);
            System.out.println("Alarm triggered for door: " + doorName);
        } else {
            System.out.println("Error: Camera service unavailable.");
        }
    }


    private void stopAlarm() {
        if (alarmControl != null) {
        	alarmControl.stopAlarm();
            System.out.println("Alarm stopped.");
        } else {
            System.out.println("Error: Camera service unavailable.");
        }
    }

    private void setAlarmSensitivity() {
        System.out.print("Enter alarm sensitivity level (1-5): ");
        int sensitivity = 0;
        try {
            sensitivity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (sensitivity < 1 || sensitivity > 5) {
                System.out.println("Invalid sensitivity level. Please enter a number between 1 and 5.");
            } else {
                if (alarmControl != null) {
                	alarmControl.setAlarmSensitivity(sensitivity);
                    System.out.println("Alarm sensitivity set to " + sensitivity);
                } else {
                    System.out.println("Error: Camera service unavailable.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a number between 1 and 5.");
            scanner.nextLine(); // Clear the buffer
        }
    }
}
