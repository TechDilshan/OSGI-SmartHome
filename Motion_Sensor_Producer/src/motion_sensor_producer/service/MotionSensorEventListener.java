package motion_sensor_producer.service;

public interface MotionSensorEventListener {
    void handleMotionSensorEvent(MotionSensorEvent event);
}
