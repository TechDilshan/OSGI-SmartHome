package smart_light_subscriber.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import smart_light_subscriber.service.LightControlService;
import smart_light_subscriber.service.LightControlServiceImpl;
import smart_light_subscriber.subscriber.SmartLightEventProcessor;

public class SmartLightActivator implements BundleActivator {

    private ServiceRegistration<LightControlService> serviceRegistration;
    private SmartLightEventProcessor eventProcessor;

    @Override
    public void start(BundleContext context) throws Exception {
        // Register Light Control Service
        LightControlService lightService = new LightControlServiceImpl();
        serviceRegistration = context.registerService(LightControlService.class, lightService, null);

        // Start event processing (subscribe to motion events)
        eventProcessor = new SmartLightEventProcessor(context, lightService);
        eventProcessor.startProcessing();

        System.out.println("Smart Light Controller Subscriber Started");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        if (serviceRegistration != null) {
            serviceRegistration.unregister();
        }
        if (eventProcessor != null) {
            eventProcessor.stopProcessing();
        }
        System.out.println("Smart Light Controller Subscriber Stopped");
    }
}
