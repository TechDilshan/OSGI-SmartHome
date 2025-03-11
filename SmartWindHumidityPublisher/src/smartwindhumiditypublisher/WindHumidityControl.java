package smartwindhumiditypublisher;

public interface WindHumidityControl {
	void setWindSpeed(String room, int speed);
    int getWindSpeed(String room);

    void setHumidity(String room, int humidity);
    int getHumidity(String room);

    void displayAllWindHumidity();
}
