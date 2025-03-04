package motion_sensor_producer.producer;

import org.osgi.framework.BundleContext;
import motion_sensor_producer.service.MotionSensorEventImpl;
import motion_sensor_producer.service.MotionSensorEvent;

public class Motion_Sensor_Producer {

    private final MotionSensorEventPublisher eventPublisher;
    private volatile boolean running = true;

    public Motion_Sensor_Producer(BundleContext context) {
        this.eventPublisher = new MotionSensorEventPublisher(context);
    }

    public void start() {
        new Thread(() -> {
            try {
                while (running) {
                    if (eventPublisher != null && eventPublisher.isContextValid()) {
                        MotionSensorEvent event = new MotionSensorEventImpl(true);
                        System.out.println("Motion detected!");
                        eventPublisher.publishEvent(event);
                    }
                    Thread.sleep(5000); // Sleep for 5 seconds before next detection
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }).start();
    }

    public void stop() {
        running = false;
        System.out.println("Motion Sensor Producer Stopped");
    }
}
