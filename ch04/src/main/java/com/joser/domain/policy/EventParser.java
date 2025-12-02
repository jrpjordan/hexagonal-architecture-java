package com.joser.domain.policy;

import com.joser.domain.entity.Event;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public sealed interface EventParser permits RegexEventParser, SplitEventParser{

    DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.of("UTC"));

    Event parseEvent(String event);
}
