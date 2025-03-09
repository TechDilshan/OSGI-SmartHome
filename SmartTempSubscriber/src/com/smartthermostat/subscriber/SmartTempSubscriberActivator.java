package com.smartthermostat.subscriber;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.smartthermostat.publisher.ThermostatControl;

public class SmartTempSubscriberActivator implements BundleActivator {
	private ThermostatControl smartThermostatControl;
	private Scanner input = new Scanner(System.in);

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Starting Smart Thermostat Subscriber...");

		ServiceReference<?> serviceReference = context.getServiceReference(ThermostatControl.class.getName());
		smartThermostatControl = (ThermostatControl) context.getService(serviceReference);

		displayMenu();
	}

	private void displayMenu() {
		boolean running = true;

		while (running) {
			System.out.println("\nSmart Thermostat Control System");
			System.out.println("1. Turn On Thermostat");
			System.out.println("2. Turn Off Thermostat");
			System.out.println("3. Set Temperature");
			System.out.println("4. Get Thermostat Status");
			System.out.println("5. Display All Thermostats");
			System.out.println("6. Add a Room");
			System.out.println("7. Delete a Room");
			System.out.println("8. Exit");
			System.out.print("Choose an option: ");

			int choice = input.nextInt();
			input.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				turnOnThermostat();
				break;
			case 2:
				turnOffThermostat();
				break;
			case 3:
				setTemperature();
				break;
			case 4:
				getThermostatStatus();
				break;
			case 5:
				displayAllThermostats();
			    break;
			case 6:
				addRoom();
				break;
			case 7:
				deleteRoom();
				break;
			case 8:
				running = false;
				System.out.println("Exiting Smart Thermostat System...");
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private void displayAllThermostats() {
		smartThermostatControl.displayAllThermostats();		
	}

	private void turnOnThermostat() {
		System.out.print("Enter the room name: ");
		String room = input.nextLine();
		smartThermostatControl.turnOnThermostat(room);
	}

	private void turnOffThermostat() {
		System.out.print("Enter the room name: ");
		String room = input.nextLine();
		smartThermostatControl.turnOffThermostat(room);
	}

	private void setTemperature() {
		System.out.print("Enter the room name: ");
		String room = input.nextLine();
		System.out.print("Enter the temperature (°C): ");
		double temperature = input.nextDouble();
		input.nextLine(); // Consume newline
		smartThermostatControl.setTemperature(room, temperature);
	}

	private void getThermostatStatus() {
		System.out.print("Enter the room name: ");
		String room = input.nextLine();
		System.out.println(smartThermostatControl.getThermostatStatus(room));
	}
	
	private void addRoom() {
	    System.out.print("Enter the name of the new room: ");
	    String room = input.nextLine();
	    smartThermostatControl.addRoom(room);
	}

	private void deleteRoom() {
	    System.out.print("Enter the name of the room to delete: ");
	    String room = input.nextLine();
	    smartThermostatControl.deleteRoom(room);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping Smart Thermostat Subscriber...");
	}

}
