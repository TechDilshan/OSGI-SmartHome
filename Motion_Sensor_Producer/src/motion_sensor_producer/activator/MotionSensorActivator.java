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
        // Initialize the Motion Sensor Service Tracker
        serviceTracker = new MotionSensorServiceTracker(context);
        serviceTracker.openTracker(); // Open the tracker (start tracking)

        // Initialize the motion sensor producer with BundleContext
        motionSensorProducer = new Motion_Sensor_Producer(context);  // Pass context here
        motionSensorProducer.start();  // Start motion detection

        // Initialize the event publisher with BundleContext
        eventPublisher = new MotionSensorEventPublisher(context);  // Pass context to event publisher

        // Register the MotionSensorService
        MotionSensorService motionSensorService = new MotionSensorServiceImpl(eventPublisher);
        serviceRegistration = context.registerService(MotionSensorService.class, motionSensorService, null);

        System.out.println("Motion Sensor Producer Started and Service Registered");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        // Unregister the service
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
        if (motionSensorProducer != null) {
            motionSensorProducer.stop(); // Stop motion detection
        }
        if (serviceTracker != null) {
            serviceTracker.closeTracker(); // Close the tracker (stop tracking)
        }
        System.out.println("Motion Sensor Producer Stopped");
    }
}
