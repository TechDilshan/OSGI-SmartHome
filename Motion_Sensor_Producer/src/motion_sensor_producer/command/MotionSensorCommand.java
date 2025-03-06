package motion_sensor_producer.command;

import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Argument;
import motion_sensor_producer.service.MotionSensorService;

@Command(scope = "motion", description = "Control motion detection and smart light.")
public class MotionSensorCommand {

    @Argument(index = 0, name = "action", description = "Action to perform", required = true)
    private String action;

    private MotionSensorService motionSensorService;

    public void setMotionSensorService(MotionSensorService motionSensorService) {
        this.motionSensorService = motionSensorService;
    }

    public void execute() {
        if (motionSensorService == null) {
            System.out.println("MotionSensorService is not available.");
            return;
        }

        if ("start".equalsIgnoreCase(action)) {
            motionSensorService.startDetection();
            System.out.println("Motion detection started.");
        } else if ("stop".equalsIgnoreCase(action)) {
            motionSensorService.stopDetection();
            System.out.println("Motion detection stopped.");
        } else {
            System.out.println("Invalid action: " + action);
        }
    }
}
