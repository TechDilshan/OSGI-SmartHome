package com.motion.publisher;

public class MotionDetector {
    private boolean motionDetected;

    public MotionDetector() {
        this.motionDetected = false;  // Initially no motion detected
    }

    public boolean isMotionDetected() {
        return motionDetected;
    }

    public void setMotionDetected(boolean motionDetected) {
        this.motionDetected = motionDetected;
    }
}
