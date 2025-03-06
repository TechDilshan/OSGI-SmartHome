package smart_light_subscriber.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import motion_sensor_producer.service.MotionSensorService;
import smart_light_subscriber.service.SmartLightService;
import smart_light_subscriber.service.SmartLightServiceImpl;

public class SmartLightActivator implements BundleActivator {

    private ServiceRegistration<SmartLightService> serviceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Smart Light Controller Subscriber Started");

        // Get reference to MotionSensorService
        ServiceReference<MotionSensorService> motionSensorServiceReference = context.getServiceReference(MotionSensorService.class);
        MotionSensorService motionSensorService = context.getService(motionSensorServiceReference);
        
        if (motionSensorService != null) {
            motionSensorService.startDetection();
            System.out.println("Motion Sensor Service is available and started.");
        }

        // Register SmartLightService
        SmartLightService smartLightService = new SmartLightServiceImpl();
        serviceRegistration = context.registerService(SmartLightService.class, smartLightService, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
        System.out.println("Smart Light Controller Subscriber Stopped");
    }
}
