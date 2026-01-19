package com.joser.application.ports.output;

public interface NotifyEventOutputPort {

    void sendEvent(String event);
    String getEvent();
}
