package com.phaseGateTwo.cms.userprofile.dtos;

import lombok.Data;


@Data
public class UpdateProfileDetailsRequest {
    private String fullName;
    private String phoneNumber;
    private String email;
}
