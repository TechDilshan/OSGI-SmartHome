package com.motion.publisher;

public interface MotionDetectedLightControl {

    void detectMotion(String room);

    void turnOffLight(String room);

    void turnOnLight(String room);
}
