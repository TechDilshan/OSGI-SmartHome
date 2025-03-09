package com.smarthome.publisher;

public interface AlarmControl {
    void triggerAlarm(String door); // Activate the alarm for a specific door
    void stopAlarm(); // Stop the alarm
    void setAlarmSensitivity(int level); // Set sensitivity (1-5)
    void linkAlarmToCamera(boolean enable);
}
