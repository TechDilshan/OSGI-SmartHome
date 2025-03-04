package motion_sensor_producer.producer;

import java.util.Random;
import motion_sensor_producer.service.MotionSensorEvent;
import motion_sensor_producer.service.MotionSensorEventImpl;

public class MotionSensorDataGenerator {

    private Random random;

    public MotionSensorDataGenerator() {
        this.random = new Random();
    }

    // Randomly generate motion detected event
    public MotionSensorEvent generateRandomMotionData() {
        boolean motionDetected = random.nextBoolean(); // Randomly decide if motion was detected
        return new MotionSensorEventImpl(motionDetected); // Use the correct implementation
    }
}
