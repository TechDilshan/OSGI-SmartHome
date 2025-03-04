package motion_sensor_producer.service;

import motion_sensor_producer.producer.MotionSensorDataGenerator;
import motion_sensor_producer.producer.MotionSensorEventPublisher;

public class MotionSensorServiceImpl implements MotionSensorService {

    private MotionSensorEventPublisher eventPublisher;
    private MotionSensorDataGenerator dataGenerator;

    public MotionSensorServiceImpl(MotionSensorEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
        this.dataGenerator = new MotionSensorDataGenerator();
    }

    @Override
    public void startMotionDetection() {
        System.out.println("Motion detection started...");
        new Thread(() -> {
            while (true) {
                MotionSensorEvent event = dataGenerator.generateRandomMotionData();
                eventPublisher.publishEvent(event);
                try {
                    Thread.sleep(5000); // Simulate sensor data generation every 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void stopMotionDetection() {
        System.out.println("Motion detection stopped...");
    }

    @Override
    public MotionSensorEvent getCurrentSensorData() {
        // Simulate fetching current sensor data
        return dataGenerator.generateRandomMotionData();
    }
}
