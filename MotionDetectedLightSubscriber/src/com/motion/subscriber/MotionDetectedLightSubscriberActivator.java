package com.motion.subscriber;

import com.motion.publisher.MotionDetectedLightControl;
import com.smarthome.publisher.SmartLightingControl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Scanner;

public class MotionDetectedLightSubscriberActivator implements BundleActivator {
    private MotionDetectedLightControl motionDetectedLightControl;
    private SmartLightingControl smartLightingControl;
    private Scanner input = new Scanner(System.in);
    private UserInterface ui;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Motion Detected Light Subscriber...");

        // Get the MotionDetectedLightControl service from the producer (Motion Detection Publisher)
        ServiceReference<?> motionServiceReference = context.getServiceReference(MotionDetectedLightControl.class.getName());
        motionDetectedLightControl = (MotionDetectedLightControl) context.getService(motionServiceReference);

        // Get the SmartLightingControl service from the producer (Smart Lighting Publisher)
        ServiceReference<?> smartLightingServiceReference = context.getServiceReference(SmartLightingControl.class.getName());
        smartLightingControl = (SmartLightingControl) context.getService(smartLightingServiceReference);

        // Initialize the User Interface with both services
        ui = new UserInterface(motionDetectedLightControl, smartLightingControl);

        // Display menu
        displayMenu();
    }

    private void displayMenu() {
        boolean running = true;

        while (running) {
            ui.showMenu();
            System.out.print("\nChoose an option: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    detectMotion();
                    break;
                case 2:
                    turnOffLight();
                    break;
                case 3:
                    turnOnLight();
                    break;
                case 4:
                    adjustBrightness();
                    break;
                case 5:
                    displayAllLights();  // Added option for displaying all lights
                    break;
                case 6:
                    showLightStatus();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting Motion Detected Light System...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void detectMotion() {
        System.out.print("Enter the room name to detect motion: ");
        String room = input.nextLine();
        ui.detectMotion(room);
    }

    private void turnOffLight() {
        System.out.print("Enter the room name to turn off the light: ");
        String room = input.nextLine();
        ui.turnOffLight(room);
    }

    private void turnOnLight() {
        System.out.print("Enter the room name to turn on the light: ");
        String room = input.nextLine();
        ui.turnOnLight(room);

        // After turning on the light, display all lights to reflect the change in status
        displayAllLights();
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

        ui.adjustBrightness(room, level);
    }

    private void showLightStatus() {
        System.out.print("Enter the room name to show the light status: ");
        String room = input.nextLine();
        ui.showLightStatus(room);
    }

    private void displayAllLights() {
        // Call the method to display all lights in the system
        ui.displayAllLights();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Motion Detected Light Subscriber...");
    }
}
