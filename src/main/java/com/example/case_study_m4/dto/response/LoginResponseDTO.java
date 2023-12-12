package com.example.case_study_m4.dto.response;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String username;
    private String token;
    private String message;
    private String role;
}
