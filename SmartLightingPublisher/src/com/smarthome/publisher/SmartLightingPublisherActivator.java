package com.smarthome.publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SmartLightingPublisherActivator implements BundleActivator {

    ServiceRegistration smartLightingControlService;

    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Smart Lighting Publisher...");
        SmartLightingControl smartLightingControl = new LightControlImpl();
        smartLightingControlService = context.registerService(SmartLightingControl.class.getName(), smartLightingControl, null);
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Smart Lighting Publisher...");
        smartLightingControlService.unregister();
    }
}
