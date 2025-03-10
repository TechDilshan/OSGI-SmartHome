package com.smarthome.energy.subscriber;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.BundleActivator;
import com.smarthome.energy.publisher.EnergyManagementControl;


public class SmartEnergySubscriberActivator implements BundleActivator {
	
	private UserInterface userInterface;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Starting Energy Management Subscriber......");
		
		 // Get the EnergyManagementControl service
		ServiceReference<?> serviceReference = context.getServiceReference(EnergyManagementControl.class.getName());
		EnergyManagementControl energyControl = (EnergyManagementControl) context.getService(serviceReference);
		
		// Initialize the UserInterface
		userInterface = new UserInterface(energyControl);
		userInterface.start();
			
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Energy Management Subscriber......");
		
	}

}
