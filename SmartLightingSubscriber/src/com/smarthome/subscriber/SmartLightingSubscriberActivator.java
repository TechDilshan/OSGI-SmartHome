package com.smarthome.subscriber;

import com.smarthome.publisher.SmartLightingControl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import java.util.Scanner;

public class SmartLightingSubscriberActivator implements BundleActivator {

    private LightSubscriber lightSubscriber;
    private Scanner input = new Scanner(System.in);

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Smart Lighting Subscriber...");
        ServiceReference<?> serviceReference = context.getServiceReference(SmartLightingControl.class.getName());
        SmartLightingControl smartLightingControl = (SmartLightingControl) context.getService(serviceReference);

        lightSubscriber = new LightSubscriber(smartLightingControl);
        lightSubscriber.subscribeToPublisher();

        // Display initial menu
        displayMenu();
    }

    private void displayMenu() {
        boolean running = true;
        
        while (running) {
            System.out.println("\nWelcome to the Smart Lighting Control System!");
            System.out.println("1. Turn On Light");
            System.out.println("2. Turn Off Light");
            System.out.println("3. Adjust Brightness");
            System.out.println("4. Show Light Status");
            System.out.println("5. Display All Lights");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            
            int choice = input.nextInt();
            input.nextLine();  // Consume newline

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
        lightSubscriber.turnOnLight(room);
    }

    private void turnOffLight() {
        System.out.print("Enter the room name to turn off the light: ");
        String room = input.nextLine();
        lightSubscriber.turnOffLight(room);
    }

    private void adjustBrightness() {
        System.out.print("Enter the room name to adjust brightness: ");
        String room = input.nextLine();
        System.out.print("Enter brightness level (0-100): ");
        int level = input.nextInt();
        lightSubscriber.adjustBrightness(room, level);
    }

    private void showLightStatus() {
        System.out.print("Enter the room name to show the light status: ");
        String room = input.nextLine();
        lightSubscriber.showLightStatus(room);
    }

    private void displayAllLights() {
        lightSubscriber.displayAllLights();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Smart Lighting Subscriber...");
    }
}
