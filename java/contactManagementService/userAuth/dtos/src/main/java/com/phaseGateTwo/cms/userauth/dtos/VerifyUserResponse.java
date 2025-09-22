package com.phaseGateTwo.cms.userauth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyUserResponse {

    private String phoneNumber;
    private String verificationCode;

}
