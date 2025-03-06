package com.mtit.paymentpublisher;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class PaymentPublisherActivator implements BundleActivator {

	ServiceRegistration publishServiceRegistration;
	
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Payment Publisher Starting...");
		PaymentPublish paymentPublisher = new PaymentPublishImpl();
		
		publishServiceRegistration = context.registerService(PaymentPublish.class.getName(), paymentPublisher, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Payment Publisher Stopping...");
		publishServiceRegistration.unregister();
	}

}
