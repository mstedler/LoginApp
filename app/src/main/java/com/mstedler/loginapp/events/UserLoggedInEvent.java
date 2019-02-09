package com.mstedler.loginapp.events;

import com.mstedler.loginapp.pojo.User;

public class UserLoggedInEvent {
    private final User user;

    public UserLoggedInEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
