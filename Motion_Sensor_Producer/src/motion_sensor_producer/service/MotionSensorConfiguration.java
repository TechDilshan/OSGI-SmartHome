package motion_sensor_producer.service;

public class MotionSensorConfiguration {

    private int detectionFrequency;

    public MotionSensorConfiguration(int detectionFrequency) {
        this.detectionFrequency = detectionFrequency;
    }

    public int getDetectionFrequency() {
        return detectionFrequency;
    }

    public void setDetectionFrequency(int detectionFrequency) {
        this.detectionFrequency = detectionFrequency;
    }
}
