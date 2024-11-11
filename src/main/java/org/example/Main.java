package org.example;

public class Main {
    public static void main(String[] args) {
        TrafficLight[] trafficLights = {
                new TrafficLight(1, false),
                new TrafficLight(2, false),
                new TrafficLight(3, true),
                new TrafficLight(4, true)
        };

        Intersection intersection = new Intersection(trafficLights);

        intersection.simulateTraffic();

        intersection.sendEvent(1, new Event(2, 7));
        intersection.sendEvent(3, new Event(4, 3));

        trafficLights[0].setQueueLength(6);
        trafficLights[1].setQueueLength(2);
    }
}
