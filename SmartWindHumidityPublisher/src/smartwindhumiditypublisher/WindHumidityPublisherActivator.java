package smartwindhumiditypublisher;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

public class WindHumidityPublisherActivator implements BundleActivator {

	private WindHumidityControl windHumidityControl;
    private Scanner input = new Scanner(System.in);

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Wind & Humidity Subscriber...");

        ServiceReference<?> serviceReference = context.getServiceReference(WindHumidityControl.class.getName());
        windHumidityControl = (WindHumidityControl) context.getService(serviceReference);

        displayMenu();
    }

    private void displayMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\nWind & Humidity Control System");
            System.out.println("1. Set Wind Speed");
            System.out.println("2. Get Wind Speed");
            System.out.println("3. Set Humidity");
            System.out.println("4. Get Humidity");
            System.out.println("5. Display All");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    setWindSpeed();
                    break;
                case 2:
                    getWindSpeed();
                    break;
                case 3:
                    setHumidity();
                    break;
                case 4:
                    getHumidity();
                    break;
                case 5:
                    windHumidityControl.displayAllWindHumidity();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting Wind & Humidity Control System...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void setWindSpeed() {
        System.out.print("Enter the room name: ");
        String room = input.nextLine();
        System.out.print("Enter wind speed (1-5): ");
        int speed = input.nextInt();
        windHumidityControl.setWindSpeed(room, speed);
    }

    private void getWindSpeed() {
        System.out.print("Enter the room name: ");
        String room = input.nextLine();
        System.out.println("Wind Speed in " + room + ": " + windHumidityControl.getWindSpeed(room));
    }

    private void setHumidity() {
        System.out.print("Enter the room name: ");
        String room = input.nextLine();
        System.out.print("Enter humidity level (30-70%): ");
        int humidity = input.nextInt();
        windHumidityControl.setHumidity(room, humidity);
    }

    private void getHumidity() {
        System.out.print("Enter the room name: ");
        String room = input.nextLine();
        System.out.println("Humidity in " + room + ": " + windHumidityControl.getHumidity(room) + "%");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Stopping Wind & Humidity Subscriber...");
    }
}
