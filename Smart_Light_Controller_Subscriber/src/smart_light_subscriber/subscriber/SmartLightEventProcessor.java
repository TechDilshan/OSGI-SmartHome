package smart_light_subscriber.subscriber;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import motion_sensor_producer.service.MotionSensorEvent;
import smart_light_subscriber.service.LightControlService;

public class SmartLightEventProcessor {

    private BundleContext context;
    private LightControlService lightService;

    public SmartLightEventProcessor(BundleContext context, LightControlService lightService) {
        this.context = context;
        this.lightService = lightService;
    }

    public void startProcessing() {
        ServiceReference<MotionSensorEvent> ref = context.getServiceReference(MotionSensorEvent.class);
        if (ref != null) {
            MotionSensorEvent motionEvent = context.getService(ref);
            if (motionEvent.isMotionDetected()) {
                lightService.turnOn();
            } else {
                lightService.turnOff();
            }
        }
    }

    public void stopProcessing() {
        System.out.println("Stopping event processing...");
    }
}
