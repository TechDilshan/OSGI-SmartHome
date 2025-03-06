package motion_sensor_producer.service;

import motion_sensor_producer.producer.MotionSensorEventPublisher;
import motion_sensor_producer.command.MotionSensorCommand;

public class MotionSensorServiceImpl implements MotionSensorService {
    private final MotionSensorEventPublisher eventPublisher;

    public MotionSensorServiceImpl(MotionSensorEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publishMotionSensorEvent(String event) {
        eventPublisher.publish(event);
    }

    @Override
    public void startDetection() {
        System.out.println("Motion detection started.");
    }

    @Override
    public void processMotionCommand(MotionSensorCommand motionCommand) {
        String command = motionCommand.getCommand();
        if ("start motion".equalsIgnoreCase(command)) {
            System.out.println("Motion detected!");
            publishMotionSensorEvent("Motion Detected");
        } else if ("end motion".equalsIgnoreCase(command)) {
            System.out.println("Motion ended successfully.");
            publishMotionSensorEvent("Motion Ended");
        } else {
            System.out.println("Unknown motion command.");
        }
    }
}
