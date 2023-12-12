package com.example.case_study_m4.dto.response.user;

import lombok.Data;

@Data
public class UserCommonResponseDTO<UserRegisterResponseDTO> {
    private UserPageResponseDTO<UserRegisterResponseDTO> data;
    private boolean success;
    private String message;
}
