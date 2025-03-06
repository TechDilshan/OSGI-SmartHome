package com.smarthome.subscriber;

import com.smarthome.publisher.SmartLightingControl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class LightSubscriber {

    private SmartLightingControl smartLightingControl;
    private Map<String, Boolean> lightStatus = new HashMap<>();
    private Map<String, Integer> brightnessLevel = new HashMap<>();
    private Map<String, String> lightOnTime = new HashMap<>();
    private Map<String, String> lightOffTime = new HashMap<>();

    public LightSubscriber(SmartLightingControl smartLightingControl) {
        this.smartLightingControl = smartLightingControl;
    }

    // Simulating subscribing to the publisher
    public void subscribeToPublisher() {
        System.out.println("Subscribed to Smart Lighting Control service.");
    }

    public void turnOnLight(String room) {
        if (lightStatus.getOrDefault(room, false)) {
            System.out.println("Light in " + room + " is alreadyff ON or room doesn't exist.");
        } else {
            smartLightingControl.turnOnLight(room);
            lightStatus.put(room, true);
            lightOnTime.put(room, getCurrentTime());
            lightOffTime.put(room, "N/A");
        }
    }

    public void turnOffLight(String room) {
        if (!lightStatus.getOrDefault(room, true)) {
            System.out.println("Light in " + room + " is already OFF or room doesn't exist.");
        } else {
            smartLightingControl.turnOffLight(room);
            lightStatus.put(room, false);
            lightOffTime.put(room, getCurrentTime());
            lightOnTime.put(room, "N/A");
        }
    }

    public void adjustBrightness(String room, int level) {
        if (!lightStatus.containsKey(room)) {
            System.out.println("Room doesn't exist.");
        } else {
            smartLightingControl.adjustBrightness(room, level);
            brightnessLevel.put(room, level);
        }
    }

    public void showLightStatus(String room) {
        if (!lightStatus.containsKey(room)) {
            System.out.println("Room doesn't exist.");
        } else {
            if (lightStatus.get(room)) {
                System.out.println("Light in " + room + " is ON with brightness: " + brightnessLevel.get(room) + "% (" + getStars(brightnessLevel.get(room)) + ")");
                System.out.println("Light On Time: " + lightOnTime.get(room));
                System.out.println("Light Off Time: " + lightOffTime.get(room));
            } else {
                System.out.println("Light in " + room + " is OFF.");
                System.out.println("Light On Time: N/A");
                System.out.println("Light Off Time: " + lightOffTime.get(room));
            }
        }
    }

    public void displayAllLights() {
        // Define column widths for proper alignment
        int colWidth1 = 15; // Room Name
        int colWidth2 = 12; // Light Status
        int colWidth3 = 20; // Brightness Level
        int colWidth4 = 18; // Light On Time
        int colWidth5 = 18; // Light Off Time

        // Define format for table structure
        String format = "| %-"+colWidth1+"s | %-"+colWidth2+"s | %-"+colWidth3+"s | %-"+colWidth4+"s | %-"+colWidth5+"s |\n";

        // Print table header
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf(format, "Room Name", "Light Status", "Brightness Level", "Light On Time", "Light Off Time");
        System.out.println("---------------------------------------------------------------------------------------------------");

        for (Map.Entry<String, Boolean> entry : lightStatus.entrySet()) {
            String room = entry.getKey();
            Boolean status = entry.getValue();
            int brightness = brightnessLevel.getOrDefault(room, 100);
            String onTime = lightOnTime.getOrDefault(room, "N/A");
            String offTime = lightOffTime.getOrDefault(room, "N/A");

            String brightnessStars = getStars(brightness);
            String brightnessLevelFormatted = String.format("%-3d%% (%-10s)", brightness, brightnessStars);

            if (status) {
                System.out.printf(format, room, "ON", brightnessLevelFormatted, onTime, "N/A");
            } else {
                System.out.printf(format, room, "OFF", brightnessLevelFormatted, "N/A", offTime);
            }
        }

        System.out.println("---------------------------------------------------------------------------------------------------");
    }


    // Helper function to generate star representation of brightness
    private String getStars(int brightness) {
        int stars = brightness / 10;
        StringBuilder starString = new StringBuilder();
        for (int i = 0; i < stars; i++) {
            starString.append("*");
        }
        return starString.toString();
    }

    // Helper function to get current time (for demonstration purposes)
    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a"); // Format: 02:50 PM
        return sdf.format(Calendar.getInstance().getTime());
    }
}
