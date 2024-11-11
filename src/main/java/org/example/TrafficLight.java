package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TrafficLight implements Runnable {
    protected final int id;
    private final boolean isPedestrian;
    private String state;
    private final BlockingQueue<Event> eventQueue;
    private final AtomicInteger queueLength;
    private int greenTime;
    private final int baseGreenTime;

    public TrafficLight(int id, boolean isPedestrian) {
        this.id = id;
        this.isPedestrian = isPedestrian;
        this.state = "red";
        this.eventQueue = new LinkedBlockingQueue<>();
        this.queueLength = new AtomicInteger(0);
        this.baseGreenTime = isPedestrian ? 10 : 20;
        this.greenTime = baseGreenTime;
    }

    public void setQueueLength(int length) {
        queueLength.set(length);
    }

    public void receiveEvent(Event event) {
        eventQueue.add(event);
    }

    private void adjustGreenTime() {
        if (queueLength.get() > 10) {
            greenTime = baseGreenTime + 10;
        } else {
            greenTime = baseGreenTime;
        }
    }

    private void processEvent(Event event) {
        if (event.getData() > 10) {
            greenTime = baseGreenTime + 10;
        } else {
            greenTime = baseGreenTime;
        }
    }

    private void switchLight() {
        state = state.equals("red") ? "green" : "red";
        System.out.println("Traffic light " + id + " switched to " + state + " with green time: " + greenTime);
    }

    @Override
    public void run() {
        try {
            while (true) {
                adjustGreenTime();
                switchLight();
                Thread.sleep(greenTime * 1000L);

                while (!eventQueue.isEmpty()) {
                    Event event = eventQueue.take();
                    processEvent(event);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}