package com.example.case_study_m4.dto.request.user;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
}
