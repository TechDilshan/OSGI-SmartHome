package motion_sensor_producer.producer;

import org.osgi.framework.BundleContext;

public class MotionSensorEventPublisher {

    private final BundleContext context;

    public MotionSensorEventPublisher(BundleContext context) {
        this.context = context;
    }

    public void publish(String event) {
        // Implement the publishing logic to notify other services
        System.out.println("Motion Sensor Event Published: " + event);
    }
}
