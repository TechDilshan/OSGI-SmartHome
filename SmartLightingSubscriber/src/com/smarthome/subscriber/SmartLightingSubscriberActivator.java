package com.smarthome.subscriber;

import com.smarthome.publisher.SmartLightingControl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import java.util.Scanner;

public class SmartLightingSubscriberActivator implements BundleActivator {

    private SmartLightingControl smartLightingControl;
    private Scanner input = new Scanner(System.in);

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Smart Lighting Subscriber...");

        // Get the SmartLightingControl service from the producer (publisher)
        ServiceReference<?> serviceReference = context.getServiceReference(SmartLightingControl.class.getName());
        smartLightingControl = (SmartLightingControl) context.getService(serviceReference);

        // Display menu
        displayMenu();
    }

    private void displayMenu() {
        boolean running = true;

        while (running) {
        	System.out.println("\n\n________________________________________________________________________________________________________________________");
            System.out.println("\nWelcome to the Smart Lighting Control System!\n");
            System.out.println("1. Turn On Light");
            System.out.println("2. Turn Off Light");
            System.out.println("3. Adjust Brightness");
            System.out.println("4. Show Light Status");
            System.out.println("5. Display All Lights");
            System.out.println("6. Exit");
            System.out.print("\nChoose an option: ");

            int choice = input.nextInt();
            input.nextLine(); // Consume newline
           
            switch (choice) {
                case 1:
                    turnOnLight();
                    break;
                case 2:
                    turnOffLight();
                    break;
                case 3:
                    adjustBrightness();
                    break;
                case 4:
                    showLightStatus();
                    break;
                case 5:
                    displayAllLights();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting Smart Lighting Control System...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void turnOnLight() {
        System.out.print("Enter the room name to turn on the light: ");
        String room = input.nextLine();
        smartLightingControl.turnOnLight(room);
        System.out.println("Light turned ON in " + room);
    }

    private void turnOffLight() {
        System.out.print("Enter the room name to turn off the light: ");
        String room = input.nextLine();
        smartLightingControl.turnOffLight(room);
        System.out.println("Light turned OFF in " + room);
    }

    private void adjustBrightness() {
        System.out.print("Enter the room name to adjust brightness: ");
        String room = input.nextLine();
        System.out.print("Enter brightness level (0-100): ");
        int level = input.nextInt();
        input.nextLine(); // Consume newline

        if (level < 0 || level > 100) {
            System.out.println("Invalid brightness level. Please enter a value between 0 and 100.");
            return;
        }

        smartLightingControl.adjustBrightness(room, level);
        System.out.println("Brightness in " + room + " set to " + level + "%");
    }

    private void showLightStatus() {
        System.out.print("Enter the room name to show the light status: ");
        String room = input.nextLine();
        String status = smartLightingControl.showLightStatus(room);
        System.out.println("\nLight status in " + room + ": " + status);
    }

    private void displayAllLights() {
        smartLightingControl.displayAllLights();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Smart Lighting Subscriber...");
    }
}
