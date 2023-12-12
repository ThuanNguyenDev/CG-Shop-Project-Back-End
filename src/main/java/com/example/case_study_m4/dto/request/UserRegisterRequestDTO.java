package com.example.case_study_m4.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequestDTO {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String address;
}
