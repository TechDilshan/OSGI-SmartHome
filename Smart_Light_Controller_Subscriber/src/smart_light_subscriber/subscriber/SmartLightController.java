package smart_light_subscriber.subscriber;

public class SmartLightController {
    
    public void turnOn() {
        System.out.println("Smart Light turned ON");
    }

    public void turnOff() {
        System.out.println("Smart Light turned OFF");
    }

    public void adjustBrightness(int level) {
        System.out.println("Smart Light brightness adjusted to: " + level + "%");
    }
}
