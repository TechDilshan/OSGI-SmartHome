package motion_sensor_producer.service;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import motion_sensor_producer.service.MotionSensorService;

public class MotionSensorServiceTracker {
    private final ServiceTracker<MotionSensorService, MotionSensorService> tracker;

    public MotionSensorServiceTracker(BundleContext context) {
        tracker = new ServiceTracker<>(context, MotionSensorService.class, null);
    }

    public void openTracker() {
        tracker.open();
    }

    public void closeTracker() {
        tracker.close();
    }
}
