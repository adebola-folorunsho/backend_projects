package com.phaseGateTwo.cms.userauth.dtos;

import lombok.Data;
@Data
public class OtpValidationRequest {
    private String phoneNumber;
    private String otp;
}
