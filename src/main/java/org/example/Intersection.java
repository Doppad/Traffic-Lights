package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Intersection {
    private final TrafficLight[] trafficLights;

    public Intersection(TrafficLight[] trafficLights) {
        this.trafficLights = trafficLights;
    }

    public void simulateTraffic() {
        ExecutorService executor = Executors.newFixedThreadPool(trafficLights.length);

        for (TrafficLight light : trafficLights) {
            executor.submit(light);
        }

        executor.shutdown();
    }

    public void sendEvent(int receiverId, Event event) {
        for (TrafficLight light : trafficLights) {
            if (light.id == receiverId) {
                light.receiveEvent(event);
                break;
            }
        }
    }
}
