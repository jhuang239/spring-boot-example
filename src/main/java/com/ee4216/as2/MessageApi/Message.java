package com.ee4216.as2.MessageApi;

public class Message {
    private String message;
    private String uuid;

    public Message(String message, String uuid) {
        this.message = message;
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public String getUUID() {
        return uuid;
    }

}
