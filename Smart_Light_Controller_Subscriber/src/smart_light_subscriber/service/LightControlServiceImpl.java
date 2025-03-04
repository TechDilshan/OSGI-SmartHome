package smart_light_subscriber.service;

import smart_light_subscriber.subscriber.SmartLightController;

public class LightControlServiceImpl implements LightControlService {

    private SmartLightController controller = new SmartLightController();

    @Override
    public void turnOn() {
        controller.turnOn();
    }

    @Override
    public void turnOff() {
        controller.turnOff();
    }

    @Override
    public void adjustBrightness(int level) {
        controller.adjustBrightness(level);
    }
}
