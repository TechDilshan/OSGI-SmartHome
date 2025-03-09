package com.smarthome.publisher;

public class CameraPublisher implements CameraControl {
    private boolean isCameraOn = false;

    @Override
    public void turnOnCamera(String door) {
        if (!isCameraOn) {
            isCameraOn = true;
            System.out.println("\n🔴 Camera is now ON. Recording unauthorized access at " + door + "...");
        } else {
            System.out.println("\n⚠ Camera is already ON.");
        }
    }

    @Override
    public void turnOffCamera() {
        if (isCameraOn) {
            isCameraOn = false;
            System.out.println("\n🟢 Camera is now OFF.");
        } else {
            System.out.println("\n⚠ Camera is already OFF.");
        }
    }
}
