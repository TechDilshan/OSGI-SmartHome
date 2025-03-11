package com.smarthome.energygrid.publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SmartEnergyGridPublisherActivator implements BundleActivator{
	
	private ServiceRegistration<?> registration;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Starting Energy Grid Management Publisher......");
		//Register the EnergyGridManagementControl Service
		EnergyGridManagementControl energyGridControl = new EnergyGridManagementControlImpl();
		registration = context.registerService(EnergyGridManagementControl.class.getName(), energyGridControl, null);
		System.out.println("EnergyGridManagementControl Service Registerd");
		
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stop Energy Grid Management Publisher......");
		registration.unregister();
		
	}

}
