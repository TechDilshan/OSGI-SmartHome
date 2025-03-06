package motion_sensor_producer.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import motion_sensor_producer.producer.MotionSensorEventPublisher;
import motion_sensor_producer.producer.Motion_Sensor_Producer;
import motion_sensor_producer.service.MotionSensorService;
import motion_sensor_producer.service.MotionSensorServiceImpl;
import motion_sensor_producer.service.MotionSensorServiceTracker;

public class MotionSensorActivator implements BundleActivator {

    private ServiceRegistration<MotionSensorService> serviceRegistration;
    private Motion_Sensor_Producer motionSensorProducer;
    private MotionSensorEventPublisher eventPublisher;
    private MotionSensorServiceTracker serviceTracker;

    @Override
    public void start(BundleContext context) throws Exception {
        // Initialize service tracker
        serviceTracker = new MotionSensorServiceTracker(context);
        serviceTracker.openTracker();
        System.out.println("Service tracker started");

        // Initialize motion sensor producer
        motionSensorProducer = new Motion_Sensor_Producer(context);
        motionSensorProducer.start();
        System.out.println("Motion Sensor Producer started");

        // Initialize event publisher
        eventPublisher = new MotionSensorEventPublisher(context);

        // Register the MotionSensorService
        MotionSensorService motionSensorService = new MotionSensorServiceImpl(eventPublisher);
        serviceRegistration = context.registerService(MotionSensorService.class, motionSensorService, null);
        System.out.println("Motion Sensor Service registered");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Unregister the service
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
            System.out.println("Motion Sensor Service unregistered");
        }

        // Stop the motion sensor producer
        if (motionSensorProducer != null) {
            motionSensorProducer.stop();
            System.out.println("Motion Sensor Producer stopped");
        }

        // Close the service tracker
        if (serviceTracker != null) {
            serviceTracker.closeTracker();
            System.out.println("Service tracker stopped");
        }
    }
}
