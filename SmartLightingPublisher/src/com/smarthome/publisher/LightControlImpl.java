package com.smarthome.publisher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LightControlImpl implements SmartLightingControl {

    private Map<String, Light> lights = new HashMap<>();

    public LightControlImpl() {
        lights.put("Living Room", new Light("Living Room", 0, false));
        lights.put("Bedroom", new Light("Bedroom", 0, false));
        lights.put("Kitchen", new Light("Kitchen", 0, false));
    }

    @Override
    public void turnOnLight(String room) {
        Light light = lights.get(room);
        if (light != null && !light.isOn()) {
            light.turnOn();
            System.out.println("\nLight in " + room + " turned ON.");
        } else {
            System.out.println("\nLight in " + room + " is already ON or room doesn't exist.");
        }
    }

    @Override
    public void turnOffLight(String room) {
        Light light = lights.get(room);
        if (light != null && light.isOn()) {
            light.turnOff();
            System.out.println("\nLight in " + room + " turned OFF.");
        } else {
            System.out.println("\nLight in " + room + " is already OFF or room doesn't exist.");
        }
    }

    @Override
    public void adjustBrightness(String room, int level) {
        Light light = lights.get(room);
        if (light != null) {
            light.setBrightness(level);
            System.out.println("\nBrightness in " + room + " set to " + level + "%.");
        } else {
            System.out.println("\nRoom doesn't exist.");
        }
    }

    @Override
    public String showLightStatus(String room) {
        Light light = lights.get(room);
        if (light != null) {
            if (light.isOn()) {
                System.out.println("\nLight in " + room + " is ON with brightness: " + light.getBrightness() + "% (" + light.getBrightnessStars() + ").");
                System.out.println("Light On Time: " + light.getLightOnTime());
                System.out.println("Light Off Time: N/A");
            } else {
                System.out.println("\nLight in " + room + " is OFF.");
                System.out.println("Light On Time: N/A");
                System.out.println("Light Off Time: " + light.getLightOffTime());
            }
        } else {
            System.out.println("\nRoom doesn't exist.");
        }
		return room;
    }

    @Override
    public void displayAllLights() {
        // Define fixed column widths
        int colWidth1 = 15; // Room Name
        int colWidth2 = 12; // Light Status
        int colWidth3 = 20; // Brightness Level
        int colWidth4 = 18; // Light On Time
        int colWidth5 = 18; // Light Off Time

        // Header format
        System.out.println("\n\n####################################################################################################################\n");
        System.out.println("---------------------------------------------------------------------------------------------------");
        String format = "| %-"+colWidth1+"s | %-"+colWidth2+"s | %-"+colWidth3+"s | %-"+colWidth4+"s | %-"+colWidth5+"s |\n";
        
        // Print table header
        System.out.printf(format, "Room Name", "Light Status", "Brightness Level", "Light On Time", "Light Off Time");
        System.out.println("---------------------------------------------------------------------------------------------------");

        for (Light light : lights.values()) {
            String lightStatus = light.isOn() ? "ON" : "OFF";
            String onTime = light.isOn() ? light.getLightOnTime() : "N/A";
            String offTime = light.isOff() ? light.getLightOffTime() : "N/A";
            String brightnessLevel = String.format("%-3d%% (%-10s)", light.getBrightness(), light.getBrightnessStars());

            // Print each row
            System.out.printf(format, light.getRoomName(), lightStatus, brightnessLevel, onTime, offTime);
        }

        System.out.println("---------------------------------------------------------------------------------------------------");
    }





    private class Light {
        private String roomName;
        private int brightness;
        private boolean isOn;
        private String lightOnTime;
        private String lightOffTime;

        public Light(String roomName, int brightness, boolean isOn) {
            this.roomName = roomName;
            this.brightness = brightness;
            this.isOn = isOn;
            this.lightOnTime = isOn ? getCurrentTime() : null;
            this.lightOffTime = !isOn ? getCurrentTime() : null;
        }

        public String getRoomName() {
            return roomName;
        }

        public int getBrightness() {
            return brightness;
        }

        public void setBrightness(int brightness) {
            this.brightness = brightness;
        }

        public boolean isOn() {
            return isOn;
        }

        public boolean isOff() {
            return !isOn;
        }

        public String getLightOnTime() {
            return lightOnTime;
        }

        public String getLightOffTime() {
            return lightOffTime;
        }

        public String getBrightnessStars() {
            StringBuilder stars = new StringBuilder();
            for (int i = 0; i < brightness / 10; i++) {
                stars.append("*");
            }
            return stars.toString();
        }

        public void turnOn() {
            this.isOn = true;
            this.lightOnTime = getCurrentTime();
        }

        public void turnOff() {
            this.isOn = false;
            this.lightOffTime = getCurrentTime();
        }

        private String getCurrentTime() {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            return sdf.format(new Date());
        }
    }
}
