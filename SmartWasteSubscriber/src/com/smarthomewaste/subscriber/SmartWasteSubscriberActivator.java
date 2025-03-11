package com.smarthomewaste.subscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.smarthomewaste.publisher.SmartBinControl;

public class SmartWasteSubscriberActivator implements BundleActivator {

    private ServiceReference<SmartBinControl> serviceReference;
    private SmartBinControl binControl;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Smart Waste Subscriber...");

        // Get reference to the SmartBinControl service
        serviceReference = context.getServiceReference(SmartBinControl.class);
        if (serviceReference != null) {
            binControl = context.getService(serviceReference);
            if (binControl != null) {
                System.out.println("Smart Bin Control service acquired successfully.");
                System.out.println();

                // Create user interface and interact with the service
                UserInterface ui = new UserInterface(binControl);
                ui.showMenu();  // Show the interactive menu to the user
            } else {
                System.out.println("Failed to acquire Smart Bin Control service.");
            }
        } else {
            System.out.println("Smart Bin Control service reference not found.");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Smart Waste Subscriber...");

        if (serviceReference != null) {
            context.ungetService(serviceReference);
            System.out.println("Smart Bin Control service released.");
        }
    }
}
