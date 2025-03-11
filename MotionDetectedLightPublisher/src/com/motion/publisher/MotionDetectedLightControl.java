package com.motion.publisher;

public interface MotionDetectedLightControl {

    // Method to detect motion in a specific room
    void detectMotion(String room);

    // Method to turn off the light in a specific room
    void turnOffLight(String room);

    // Method to turn on the light in a specific room
    void turnOnLight(String room);
}
