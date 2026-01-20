package com.joser.topologyinventory.domain.exception;

import com.joser.topologyinventory.domain.vo.IP;

public class GenericSpecificationException extends RuntimeException{

    public GenericSpecificationException(String message) {
        super(message);
    }
}
