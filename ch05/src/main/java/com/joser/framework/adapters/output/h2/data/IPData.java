package com.joser.framework.adapters.output.h2.data;

import com.joser.domain.valueobject.Protocol;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Embeddable
@Getter
public class IPData {

    private String address;

    @Enumerated(EnumType.STRING)
    @Embedded
    private Protocol protocol;

    private IPData(String address) {
        if(address == null) {
            throw new IllegalArgumentException("Null IP address");
        }
        this.address = address;
        if (address.length() <= 15) {
            this.protocol = Protocol.IPV4;
        } else {
            this.protocol = Protocol.IPV6;
        }
    }

    public IPData() {}

    public static IPData fromAddress(String address) {
        return new IPData(address);
    }
}
