package com.smartthermostat.publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SmartTempPublisherActivator implements BundleActivator{
	private ServiceRegistration<?> registration;

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("Starting Smart Thermostat Publisher...");
        ThermostatControl thermostatControl = new ThermostatControlImpl();
        registration = context.registerService(ThermostatControl.class.getName(), thermostatControl, null);
        System.out.println("SmartThermostatControl service registered.");		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping Smart Thermostat Publisher...");
        registration.unregister();		
	}

}
