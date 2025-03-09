package com.smarthome.publisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class SmartSecurityPublisherActivator implements BundleActivator {
    private ServiceRegistration<?> doorSensorRegistration, cameraRegistration, alarmRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("✅ Starting Smart Security Publisher...");

        CameraControl cameraControl = new CameraPublisher();
        AlarmControl alarmControl = new AlarmPublisher(cameraControl);
        DoorSensorControl doorSensorControl = new DoorSensorPublisher(cameraControl, alarmControl);

        doorSensorRegistration = context.registerService(DoorSensorControl.class, doorSensorControl, null);
        cameraRegistration = context.registerService(CameraControl.class, cameraControl, null);
        alarmRegistration = context.registerService(AlarmControl.class, alarmControl, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        doorSensorRegistration.unregister();
        cameraRegistration.unregister();
        alarmRegistration.unregister();
        System.out.println("⏹ Stopping Smart Security Publisher...");
    }
}
