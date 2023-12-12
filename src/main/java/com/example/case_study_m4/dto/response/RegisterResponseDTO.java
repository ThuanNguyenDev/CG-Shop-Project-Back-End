package com.example.case_study_m4.dto.response;

import lombok.Data;

@Data
public class RegisterResponseDTO {
    private int id;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
}
