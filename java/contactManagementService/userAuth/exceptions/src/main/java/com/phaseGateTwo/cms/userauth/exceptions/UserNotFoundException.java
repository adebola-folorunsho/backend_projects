package com.phaseGateTwo.cms.userauth.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String phone) { super("User not found: " + phone); }
}
