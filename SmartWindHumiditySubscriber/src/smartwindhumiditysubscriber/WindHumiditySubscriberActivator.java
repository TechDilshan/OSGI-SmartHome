package smartwindhumiditysubscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class WindHumiditySubscriberActivator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		WindHumiditySubscriberActivator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		WindHumiditySubscriberActivator.context = null;
	}

}
