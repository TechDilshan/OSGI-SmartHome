package smart_light_subscriber.service;

public interface LightControlService {
    void turnOn();
    void turnOff();
    void adjustBrightness(int level);
}
