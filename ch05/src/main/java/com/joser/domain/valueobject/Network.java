package com.joser.domain.valueobject;

public record Network(IP address, String name, int cidr) {

    // Constructor validation, it takes the same parameters as the record components
    public Network {
        if (cidr < 0 || cidr > 32) {
            throw new IllegalArgumentException("CIDR must be between 0 and 32");
        }
    }

    @Override
    public String toString() {
        return "Network{" +
                "address=" + address +
                ", name='" + name + '\'' +
                ", cidr=" + cidr +
                '}';
    }
}
