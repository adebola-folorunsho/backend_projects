package com.phaseGateTwo.cms.userauth.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String phone) { super("User already exists"); }
}
