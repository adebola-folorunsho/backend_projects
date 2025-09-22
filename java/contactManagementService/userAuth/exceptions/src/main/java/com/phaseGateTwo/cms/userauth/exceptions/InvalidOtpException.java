package com.phaseGateTwo.cms.userauth.exceptions;

public class InvalidOtpException extends RuntimeException {
    public InvalidOtpException() { super("Invalid or expired OTP"); }
}
