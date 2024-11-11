package org.example;

public class Event {
    private final int senderId;
    private final int data;

    public Event(int senderId, int data) {
        this.senderId = senderId;
        this.data = data;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getData() {
        return data;
    }
}
