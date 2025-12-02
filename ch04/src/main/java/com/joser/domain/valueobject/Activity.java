package com.joser.domain.valueobject;

public class Activity {

    private String srcHost;
    private String dstHost;

    public Activity (String srcHost, String dstHost) {
        this.srcHost = srcHost;
        this.dstHost = dstHost;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "srcHost='" + srcHost + '\'' +
                ", dstHost='" + dstHost + '\'' +
                '}';
    }
}
