package com.phaseGateTwo.cms.userprofile.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditContactResponse {

    private String id;        // contact ID
    private String fullName;
    private String phone;
    private String email;
}
