package com.joser.domain.entity;

import com.joser.domain.policy.RegexEventParser;
import com.joser.domain.policy.SplitEventParser;
import com.joser.domain.valueobject.Activity;
import com.joser.domain.valueobject.EventId;
import com.joser.domain.valueobject.ParsePolicyType;
import com.joser.domain.valueobject.Protocol;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Event implements Comparable<Event>{

    private EventId eventId;
    private OffsetDateTime timestamp;
    private Protocol protocol;
    private Activity activity;

    public Event(OffsetDateTime timestamp, EventId id, Protocol protocol, Activity activity){
        this.timestamp = timestamp;
        this.eventId = id;
        this.protocol = protocol;
        this.activity = activity;
    }

    public static Event parsedEvent(String unparsedEvent, ParsePolicyType policy) {
        return switch (policy) {
            case REGEX -> new RegexEventParser().parseEvent(unparsedEvent);
            case SPLIT -> new SplitEventParser().parseEvent(unparsedEvent);
        };
    }

    @Override
    public int compareTo(Event o) {
        return 0;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event event) {
            return (event.timestamp.equals(this.timestamp)
                    && event.eventId.equals(this.eventId)
                    && event.protocol.equals(this.protocol)
                    && event.activity.equals(this.activity)
            );
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, eventId, protocol, activity) + 31;
    }
}
