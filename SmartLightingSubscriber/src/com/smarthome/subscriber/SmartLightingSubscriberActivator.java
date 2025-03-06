package com.smarthome.subscriber;

import com.smarthome.publisher.SmartLightingControl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Scanner;

public class SmartLightingSubscriberActivator implements BundleActivator {

    ServiceReference serviceReference;

    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Smart Lighting Subscriber...");
        serviceReference = context.getServiceReference(SmartLightingControl.class.getName());
        SmartLightingControl lightingControl = (SmartLightingControl) context.getService(serviceReference);

        Scanner input = new Scanner(System.in);
        UserInterface ui = new UserInterface(lightingControl, input);
        ui.displayMenu();
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Smart Lighting Subscriber...");
    }
}
