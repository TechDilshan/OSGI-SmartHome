package motion_sensor_producer.service;

public class MotionSensorEventImpl implements MotionSensorEvent {

    private final boolean motionDetected;

    public MotionSensorEventImpl(boolean motionDetected) {
        this.motionDetected = motionDetected;
    }

    @Override
    public boolean isMotionDetected() {
        return motionDetected;
    }

    @Override
    public String toString() {
        return "MotionSensorEvent [motionDetected=" + motionDetected + "]";
    }
}
