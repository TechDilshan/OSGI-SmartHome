package com.motion.subscriber;

import com.motion.publisher.MotionDetectedLightControl;
import com.smarthome.publisher.SmartLightingControl;

import java.util.HashMap;
import java.util.Map;

public class UserInterface {
    private MotionDetectedLightControl motionDetectedLightControl;
    private SmartLightingControl smartLightingControl;
    private Map<String, Light> lights = new HashMap<>();  // Store light status for each room

    public UserInterface(MotionDetectedLightControl motionDetectedLightControl, SmartLightingControl smartLightingControl) {
        this.motionDetectedLightControl = motionDetectedLightControl;
        this.smartLightingControl = smartLightingControl;

        // Initialize some rooms with default values
        lights.put("Kitchen", new Light("Kitchen", "OFF", 0, "N/A", "05:31 PM"));
        lights.put("Living Room", new Light("Living Room", "OFF", 0, "N/A", "05:31 PM"));
        lights.put("Bedroom", new Light("Bedroom", "OFF", 0, "N/A", "05:31 PM"));
    }

    public void showMenu() {
        System.out.println("Welcome to the Motion Detected Light Control System!");
        System.out.println("1. Detect Motion");
        System.out.println("2. Turn Off Light");
        System.out.println("3. Turn On Light");
        System.out.println("4. Adjust Brightness");
        System.out.println("5. Display All Lights");
        System.out.println("6. Show Light Status");
        System.out.println("7. Exit");
    }

    public void detectMotion(String room) {
        motionDetectedLightControl.detectMotion(room);
        updateLightStatus(room, "OFF", 0, "N/A");
        updateLightStatus(room, "ON", 100, "12:00 PM"); 
        System.out.println("Motion detected in room: " + room);
    }

    public void turnOffLight(String room) {
        motionDetectedLightControl.turnOffLight(room);
        updateLightStatus(room, "OFF", 0, "N/A");
    }

    public void turnOnLight(String room) {
        motionDetectedLightControl.turnOnLight(room);
        updateLightStatus(room, "ON", 100, "12:00 PM"); 
    }

    public void adjustBrightness(String room, int level) {
        smartLightingControl.adjustBrightness(room, level);
        updateLightStatus(room, "ON", level, "12:00 PM");
    }

    public void showLightStatus(String room) {
        Light light = lights.get(room);
        System.out.println("Light status for room " + room + ": " + light);
    }

    public void displayAllLights() {
        System.out.println("####################################################################################################################");
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("| Room Name       | Light Status | Brightness Level     | Light On Time      | Light Off Time     |");
        System.out.println("---------------------------------------------------------------------------------------------------");
        for (Light light : lights.values()) {
            System.out.println(light);
        }
        System.out.println("---------------------------------------------------------------------------------------------------");
    }

    // Update the light's status for a room
    private void updateLightStatus(String room, String status, int brightness, String time) {
        Light light = lights.get(room);
        if (light != null) {
            light.setStatus(status);
            light.setBrightness(brightness);
            light.setOnTime(time);
        }
    }

    // Helper class to store the light status for each room
    static class Light {
        private String roomName;
        private String status;
        private int brightness;
        private String onTime;
        private String offTime;

        public Light(String roomName, String status, int brightness, String onTime, String offTime) {
            this.roomName = roomName;
            this.status = status;
            this.brightness = brightness;
            this.onTime = onTime;
            this.offTime = offTime;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setBrightness(int brightness) {
            this.brightness = brightness;
        }

        public void setOnTime(String onTime) {
            this.onTime = onTime;
        }

        @Override
        public String toString() {
            return String.format("| %-15s | %-12s | %-19d | %-17s | %-17s |", roomName, status, brightness, onTime, offTime);
        }
    }
}
