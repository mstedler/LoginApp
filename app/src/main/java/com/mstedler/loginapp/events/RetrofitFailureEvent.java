package com.mstedler.loginapp.events;

public class RetrofitFailureEvent {
    private String message;

    public RetrofitFailureEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
