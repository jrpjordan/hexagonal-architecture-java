package com.joser.domain.valueobject;

public class IP {

    private final String address;
    private final Protocol protocol;

    public IP(String address) {
        if (address == null)
            throw new IllegalArgumentException("address cannot be null");
        this.address = address;
        if (address.length() <= 15) {
            this.protocol = Protocol.IPV4;
        } else {
            this.protocol = Protocol.IPV6;
        }
    }

    public static IP fromAddress(String address) {
        return new IP(address);
    }

    public String getAddress() {
        return address;
    }
    public Protocol getProtocol() {
        return protocol;
    }

    @Override
    public String toString() {
        return "IP{" +
                "address='" + address + '\'' +
                ", protocol=" + protocol +
                '}';
    }
}
