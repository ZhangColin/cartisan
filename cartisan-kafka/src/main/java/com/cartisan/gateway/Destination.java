package com.cartisan.gateway;

/**
 * @author colin
 */
public class Destination {
    private final String host;
    private final int port;
    private final String topic;

    public Destination(String host, int port, String topic) {
        this.host = host;
        this.port = port;
        this.topic = topic;
    }

    public String server() {
        return String.format("&s:%s", host, port);
    }

    public String topic() {
        return topic;
    }
}
