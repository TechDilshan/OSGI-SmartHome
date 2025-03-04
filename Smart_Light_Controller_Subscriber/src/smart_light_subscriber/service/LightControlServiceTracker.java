package smart_light_subscriber.service;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;


public class LightControlServiceTracker extends ServiceTracker<LightControlService, LightControlService> {

    public LightControlServiceTracker(BundleContext context) {
        super(context, LightControlService.class, null);
    }

    @Override
    public LightControlService addingService(org.osgi.framework.ServiceReference<LightControlService> reference) {
        LightControlService service = super.addingService(reference);
        System.out.println("Light Control Service Registered: " + service);
        return service;
    }

    @Override
    public void removedService(org.osgi.framework.ServiceReference<LightControlService> reference, LightControlService service) {
        System.out.println("Light Control Service Unregistered: " + service);
        super.removedService(reference, service);
    }
}
