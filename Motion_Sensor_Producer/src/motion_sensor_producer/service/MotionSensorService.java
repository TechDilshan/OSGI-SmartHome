package motion_sensor_producer.service;

import motion_sensor_producer.command.MotionSensorCommand;

public interface MotionSensorService {
    void publishMotionSensorEvent(String event);
    void startDetection();
    void processMotionCommand(MotionSensorCommand motionCommand);
}
