package org.metahut.starfish.message.api;

public class MessageException extends Exception {

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
