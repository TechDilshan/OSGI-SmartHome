package com.smarthome.energygrid.subscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.smarthome.energygrid.publisher.EnergyGridManagementControl;

public class SmartEnergyGridSubscriberActivator implements BundleActivator{
	
	private UserInterface userInterface;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Energy Grid Management Subscriber...");

        ServiceReference<?> serviceReference = context.getServiceReference(EnergyGridManagementControl.class.getName());
        EnergyGridManagementControl energyControl = (EnergyGridManagementControl) context.getService(serviceReference);

        userInterface = new UserInterface(energyControl);
        userInterface.start();
    }
    
    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Energy Grid Management Subscriber...");
    }

}
