package com.smarthome.publisher;

import java.util.HashMap;
import java.util.Map;

public class DoorSensorPublisher implements DoorSensorControl {
    private Map<String, Door> doors = new HashMap<>();
    private CameraControl camera;
    private AlarmControl alarm;

    public DoorSensorPublisher(CameraControl camera, AlarmControl alarm) {
        this.camera = camera;
        this.alarm = alarm;

        doors.put("Front Door", new Door("Front Door", false));
        doors.put("Back Door", new Door("Back Door", false));
        doors.put("Garage Door", new Door("Garage Door", false));
        doors.put("Side Door", new Door("Side Door", false));
    }

    @Override
    public void openDoor(String door, boolean isAuthorized) {
        Door d = doors.get(door);
        if (d != null && !d.isOpen()) {
            d.setOpen(true);
            System.out.println("\n🚪 " + door + " is now OPEN.");

            if (!isAuthorized) {
                System.out.println("🚨 Unauthorized access detected at: " + door);
                camera.turnOnCamera(door);
                alarm.triggerAlarm(door);
            } else {
                System.out.println("✅ Authorized access at: " + door);
            }
        } else {
            System.out.println("\n⚠ " + door + " is already OPEN or doesn't exist.");
        }
    }

    @Override
    public void closeDoor(String door) {
        Door d = doors.get(door);
        if (d != null && d.isOpen()) {
            d.setOpen(false);
            System.out.println("\n🚪 " + door + " is now CLOSED.");
            camera.turnOffCamera();
        } else {
            System.out.println("\n⚠ " + door + " is already CLOSED or doesn't exist.");
        }
    }

    @Override
    public String showDoorStatus(String door) {
        Door d = doors.get(door);
        if (d != null) {
            String status = d.isOpen() ? "OPEN" : "CLOSED";
            System.out.println("\n" + door + " is currently " + status + ".");
            return status;
        } else {
            System.out.println("\n⚠ Door not found.");
            return "UNKNOWN";
        }
    }

    @Override
    public void displayAllDoors() {
        System.out.println("\n-------------------- Door Status --------------------");
        System.out.printf("\t| %-18s | %-12s |\n", "Door Name", "Status");
        System.out.println("\t|--------------------|--------------|");

        for (Door d : doors.values()) {
            String status = d.isOpen() ? "OPEN" : "CLOSED";
            System.out.printf("\t| %-18s | %-12s |\n", d.getName(), status);
        }

        System.out.println("\t|--------------------|--------------|");
    }

    private static class Door {
        private String name;
        private boolean open;

        public Door(String name, boolean open) {
            this.name = name;
            this.open = open;
        }

        public String getName() {
            return name;
        }

        public boolean isOpen() {
            return open;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }
    }
}
