package com.smarthome.publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SmartLightingPublisherActivator implements BundleActivator {

    private ServiceRegistration<?> registration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Smart Lighting Publisher...");
        // Register the SmartLightingControl service
        SmartLightingControl lightControl = new LightControlImpl();
        registration = context.registerService(SmartLightingControl.class.getName(), lightControl, null);
        System.out.println("SmartLightingControl service registered.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Smart Lighting Publisher...");
        registration.unregister();
    }
}
