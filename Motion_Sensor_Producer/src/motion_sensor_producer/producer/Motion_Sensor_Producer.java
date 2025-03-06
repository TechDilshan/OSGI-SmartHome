package motion_sensor_producer.producer;

import org.osgi.framework.BundleContext;

public class Motion_Sensor_Producer {

    private final BundleContext context;

    public Motion_Sensor_Producer(BundleContext context) {
        this.context = context;
    }

    public void start() {
        // Start the motion detection process
        System.out.println("Motion detection started.");
    }

    public void stop() {
        // Stop the motion detection process
        System.out.println("Motion detection stopped.");
    }
}
