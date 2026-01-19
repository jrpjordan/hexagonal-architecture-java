package com.joser.domain.valueobject;

public class EventId {

    private final String eventId;

    private EventId(String eventId) {
        this.eventId = eventId;
    }

    public static EventId of(String eventId) {
        return new EventId(eventId);
    }

    @Override
    public String toString() {
        return "EventId{" +
                "id='" + eventId + '\'' +
                '}';
    }
}
