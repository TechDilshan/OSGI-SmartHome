package com.motion.subscriber;

import com.motion.publisher.MotionDetectedLightControl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Scanner;

public class MotionDetectedLightSubscriberActivator implements BundleActivator {
    private MotionDetectedLightControl motionDetectedLightControl;
    private Scanner input = new Scanner(System.in);
    private UserInterface ui;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Motion Detected Light Subscriber...");

        // Get the MotionDetectedLightControl service from the producer (publisher)
        ServiceReference<?> serviceReference = context.getServiceReference(MotionDetectedLightControl.class.getName());
        motionDetectedLightControl = (MotionDetectedLightControl) context.getService(serviceReference);

        ui = new UserInterface(motionDetectedLightControl);

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
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Motion Detected Light Subscriber...");
    }
}
