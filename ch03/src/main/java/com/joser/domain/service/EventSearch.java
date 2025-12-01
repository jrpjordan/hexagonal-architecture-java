package com.joser.domain.service;

import com.joser.domain.entity.Event;
import com.joser.domain.valueobject.ParsePolicyType;

import java.util.ArrayList;
import java.util.List;

public class EventSearch {

    public List<Event> retrieveEvents(List<String> unparsedEvents, ParsePolicyType policyType) {
        var parsedEvents = new ArrayList<Event>();
        unparsedEvents.forEach(event -> {
            parsedEvents.add(Event.parsedEvent(event, policyType));
        });
        return parsedEvents;
    }
}
