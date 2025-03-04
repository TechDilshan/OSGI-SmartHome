package motion_sensor_producer.producer;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import motion_sensor_producer.service.MotionSensorEvent;

public class MotionSensorEventPublisher {

    private final BundleContext context;

    public MotionSensorEventPublisher(BundleContext context) {
        this.context = context;
    }

    public void publishEvent(MotionSensorEvent event) {
        if (context != null) {
            try {
                ServiceReference<?>[] refs = context.getServiceReferences(MotionSensorEvent.class.getName(), null);
                if (refs != null) {
                    for (ServiceReference<?> ref : refs) {
                        MotionSensorEvent eventService = (MotionSensorEvent) context.getService(ref);
                        if (eventService != null) {
                            System.out.println("Event published: " + event);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Error: BundleContext is no longer valid.");
        }
    }

    public boolean isContextValid() {
        return context != null && context.getBundle().getState() == org.osgi.framework.Bundle.ACTIVE;
    }
}
