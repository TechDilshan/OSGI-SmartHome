package com.smarthome.energy.publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SmartEnergyPublisherActivator implements BundleActivator{
	
	private ServiceRegistration<?> registration;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Starting Energy Management Publisher......");
		//Register the EnergyManagementControl Service
		EnergyManagementControl energyControl = new EnergyManagementControlImpl();
		registration = context.registerService(EnergyManagementControl.class.getName(), energyControl, null);
		System.out.println("EnergyManagementControl Service Registerd");
		
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Energy Management Publisher......");
		registration.unregister();
		
	}
	
	

}
