package com.motion.subscriber;

import com.motion.publisher.MotionDetectedLightControl;

import java.util.Scanner;

public class UserInterface {
    private MotionDetectedLightControl motionDetectedLightControl;
    private Scanner input = new Scanner(System.in);

    public UserInterface(MotionDetectedLightControl motionDetectedLightControl) {
        this.motionDetectedLightControl = motionDetectedLightControl;
    }

    public void showMenu() {
        System.out.println("Welcome to the Motion Detected Light Control System!");
        System.out.println("1. Detect Motion");
        System.out.println("2. Turn Off Light");
        System.out.println("3. Turn On Light");
        System.out.println("4. Exit");
    }

    public void detectMotion(String room) {
        motionDetectedLightControl.detectMotion(room);
    }

    public void turnOffLight(String room) {
        motionDetectedLightControl.turnOffLight(room);
    }

    public void turnOnLight(String room) {
        motionDetectedLightControl.turnOnLight(room);
    }
}
