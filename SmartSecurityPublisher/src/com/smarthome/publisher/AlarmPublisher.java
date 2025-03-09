package com.smarthome.publisher;

public class AlarmPublisher implements AlarmControl {
    private boolean isAlarmLinked = false;
    private int alarmSensitivity = 3;
    private CameraControl camera;

    public AlarmPublisher(CameraControl camera) {
        this.camera = camera;
    }

    @Override
    public void triggerAlarm(String door) {
        System.out.println("🚨 ALARM ACTIVATED! Intruder detected at " + door + "!");
        if (isAlarmLinked) {
            camera.turnOnCamera(door);
        }
    }

    @Override
    public void stopAlarm() {
        System.out.println("🔕 Alarm stopped.");
    }

    @Override
    public void setAlarmSensitivity(int level) {
        if (level >= 1 && level <= 5) {
            this.alarmSensitivity = level;
            System.out.println("🔧 Alarm sensitivity set to: " + level);
        } else {
            System.out.println("⚠ Invalid sensitivity level. Use 1-5.");
        }
    }

    @Override
    public void linkAlarmToCamera(boolean enable) {
        this.isAlarmLinked = enable;
        System.out.println("🔗 Alarm linked to camera: " + enable);
    }
}
