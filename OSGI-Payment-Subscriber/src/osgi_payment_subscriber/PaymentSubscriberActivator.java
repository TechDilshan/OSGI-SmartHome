package osgi_payment_subscriber;

import com.mtit.paymentpublisher.PaymentPublish;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Scanner;

public class PaymentSubscriberActivator implements BundleActivator {

	ServiceReference serviceReference;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Payment Subscriber Service...");
		serviceReference = context.getServiceReference(PaymentPublish.class.getName());
		PaymentPublish paymentPublisher = (PaymentPublish)context.getService(serviceReference);
		
		Scanner input = new Scanner(System.in);
		int num;
		
		String keyuser = "chathu";
		String keypassword = "chathu@@";
		
		System.out.print("input username :");
		String username = input.nextLine();
		
		System.out.print("input password :");
		String password = input.nextLine();
		
		System.out.println("\n @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
		
		if(username.equals(keyuser) && password.equals(keypassword)) {
			
			System.out.println("*********************Welcome To*********************");
			System.out.println("****************Ahmad Tea Sri Lanka*****************");
			System.out.println("***********NO: 303, R. A. De.Mel Mawatha*************");
			System.out.println("*************Colombo 3 0300 Colombo,**************");
			System.out.println("********************Sri Lanka**********************");
			System.out.println("**************ahmadtea@ahmadteasl.net**************");
			System.out.println("**************Call us on 0112 370 904***************\n");
			System.out.println("01.Select Payment Methods");
			System.out.println("02.Calculate the Total");
			System.out.println("03.Payment Details");
			System.out.println("04.Exit\n");
			
			System.out.print("input number here : ");
			num = input.nextInt();
			
			//Payment Methods
			if(num == 1||num == 01) {
				
				System.out.println("*********************************************");
				System.out.println("*******Payment Methods******");
				System.out.println("*********************************************");
				paymentPublisher.ViewPaymentMethods();
				
			}
			//Calculate the Total
			else if(num == 2 || num == 02) {
				
				System.out.println("*********************************************");
				System.out.println("*******Total******");
				System.out.println("*********************************************");
				paymentPublisher.CalcTotal();
				
			}
			//Payment Details
			else if(num == 3 || num == 03) {
				System.out.println("*********************************************");
				System.out.println("*******Payment Details******");
				System.out.println("*********************************************");
				paymentPublisher.PaymentDetails();
			}
			//Exit
			else if (num == 4 || num == 04) {
				
				System.out.println("!!!------Thank You------!!!");
				
			}
			else{
				System.out.println("Wrong Number Try Again With Correct Number!!!");
			}
			
		}else {
			System.out.println("wrong username or password!!!");
		}
		
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stop Payment Subscriber...");
		
	}

}
