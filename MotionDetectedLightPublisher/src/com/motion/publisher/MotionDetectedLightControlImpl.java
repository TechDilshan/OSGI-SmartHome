package com.motion.publisher;

public class MotionDetectedLightControlImpl implements MotionDetectedLightControl {
    private MotionDetector detector;

    public MotionDetectedLightControlImpl() {
        detector = new MotionDetector();
    }

    @Override
    public void detectMotion(String room) {
        // Simulate motion detection
        detector.setMotionDetected(true);
        System.out.println("Motion detected in room: " + room);
        turnOnLight(room);
    }

    @Override
    public void turnOffLight(String room) {
        System.out.println("Turning off the light in room: " + room);
    }

    @Override
    public void turnOnLight(String room) {
        if (detector.isMotionDetected()) {
            System.out.println("Turning on the light in room: " + room);
        } else {
            System.out.println("No motion detected. Light remains off in room: " + room);
        }
    }
}
