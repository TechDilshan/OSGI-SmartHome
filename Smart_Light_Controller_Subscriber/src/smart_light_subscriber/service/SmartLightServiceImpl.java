package smart_light_subscriber.service;

public class SmartLightServiceImpl implements SmartLightService {

    @Override
    public void turnOn() {
        System.out.println("Smart light turned on.");
    }

    @Override
    public void turnOff() {
        System.out.println("Smart light turned off.");
    }
}
