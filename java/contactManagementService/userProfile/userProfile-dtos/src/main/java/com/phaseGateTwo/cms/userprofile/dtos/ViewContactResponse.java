package com.phaseGateTwo.cms.userprofile.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewContactResponse {
    private String fullName;
    private String phone;
    private String email;
}