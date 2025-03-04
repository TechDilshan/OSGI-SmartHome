package motion_sensor_producer.service;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class MotionSensorServiceTracker extends ServiceTracker<MotionSensorService, MotionSensorService> {

    public MotionSensorServiceTracker(BundleContext context) {
        super(context, MotionSensorService.class, null);
    }

    @Override
    public MotionSensorService addingService(ServiceReference<MotionSensorService> reference) {
        MotionSensorService service = super.addingService(reference);
        // Logic to handle when the service is added
        System.out.println("Motion Sensor Service added.");
        return service;
    }

    @Override
    public void removedService(ServiceReference<MotionSensorService> reference, MotionSensorService service) {
        super.removedService(reference, service);
        // Logic to handle when the service is removed
        System.out.println("Motion Sensor Service removed.");
    }

    // Start tracking the service (use open() instead of start())
    public void openTracker() {
        open();
        System.out.println("Service Tracker is now open and tracking services.");
    }

    // Stop tracking the service (use close() instead of stop())
    public void closeTracker() {
        close();
        System.out.println("Service Tracker is now closed and no longer tracking services.");
    }
}
