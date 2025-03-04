package motion_sensor_producer.service;

public class MotionSensorEventImpl implements MotionSensorEvent {
    private boolean motionDetected;

    public MotionSensorEventImpl(boolean motionDetected) {
        this.motionDetected = motionDetected;
    }

    public boolean isMotionDetected() {
        return motionDetected;
    }

    @Override
    public String toString() {
        return "Motion detected: " + motionDetected;
    }
}
