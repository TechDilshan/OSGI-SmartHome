package com.smarthomewaste.publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SmartWastePublisherActivator implements BundleActivator {

    private ServiceRegistration<?> registration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Smart Waste Publisher...");
        SmartBinControl binControl = new BinControl();
        registration = context.registerService(SmartBinControl.class.getName(), binControl, null);
        System.out.println("Smart Waste Control service registered.");
        System.out.println();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Smart Waste Publisher...");
        registration.unregister();
    }
}
