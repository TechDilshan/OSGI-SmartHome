package smartwindhumiditypublisher;

import java.util.HashMap;
import java.util.Map;

public class WindHumidityControlImpl implements WindHumidityControl {

    private static class RoomCondition {
        int windSpeed;
        int humidity;

        RoomCondition() {
            this.windSpeed = 3; // Default wind speed
            this.humidity = 50; // Default humidity
        }
    }

    private Map<String, RoomCondition> roomConditions = new HashMap<>();

    public WindHumidityControlImpl() {
        roomConditions.put("Living Room", new RoomCondition());
        roomConditions.put("Bedroom", new RoomCondition());
        roomConditions.put("Kitchen", new RoomCondition());
    }

    @Override
    public void setWindSpeed(String room, int speed) {
        if (speed < 1 || speed > 5) {
            System.out.println("Invalid wind speed. Must be between 1-5.");
            return;
        }
        RoomCondition condition = roomConditions.get(room);
        if (condition != null) {
            condition.windSpeed = speed;
            System.out.println("Wind speed in " + room + " set to " + speed);
        } else {
            System.out.println("Room does not exist.");
        }
    }

    @Override
    public int getWindSpeed(String room) {
        return roomConditions.getOrDefault(room, new RoomCondition()).windSpeed;
    }

    @Override
    public void setHumidity(String room, int humidity) {
        if (humidity < 30 || humidity > 70) {
            System.out.println("Invalid humidity level. Must be between 30-70%.");
            return;
        }
        RoomCondition condition = roomConditions.get(room);
        if (condition != null) {
            condition.humidity = humidity;
            System.out.println("Humidity in " + room + " set to " + humidity + "%");
        } else {
            System.out.println("Room does not exist.");
        }
    }

    @Override
    public int getHumidity(String room) {
        return roomConditions.getOrDefault(room, new RoomCondition()).humidity;
    }

    @Override
    public void displayAllWindHumidity() {
        System.out.println("\n----------------------------");
        System.out.println("| Room Name     | Wind Speed | Humidity (%) |");
        System.out.println("----------------------------");

        for (Map.Entry<String, RoomCondition> entry : roomConditions.entrySet()) {
            String room = entry.getKey();
            RoomCondition condition = entry.getValue();
            System.out.printf("| %-12s | %-10d | %-12d |\n", room, condition.windSpeed, condition.humidity);
        }

        System.out.println("----------------------------");
    }
}