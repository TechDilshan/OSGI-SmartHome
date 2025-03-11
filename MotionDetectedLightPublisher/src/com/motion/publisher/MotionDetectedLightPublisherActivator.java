package com.motion.publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class MotionDetectedLightPublisherActivator implements BundleActivator {
    private ServiceRegistration<?> registration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Motion Detected Light Publisher...");
        // Register the MotionDetectedLightControl service
        MotionDetectedLightControl motionControl = new MotionDetectedLightControlImpl();
        registration = context.registerService(MotionDetectedLightControl.class.getName(), motionControl, null);
        System.out.println("MotionDetectedLightControl service registered.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Motion Detected Light Publisher...");
        registration.unregister();
    }
}
