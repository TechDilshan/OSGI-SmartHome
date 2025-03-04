package motion_sensor_producer.service;


public interface MotionSensorService {
    void startMotionDetection();
    void stopMotionDetection();
    MotionSensorEvent getCurrentSensorData();
}
