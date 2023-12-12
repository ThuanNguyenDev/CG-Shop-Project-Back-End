package com.example.case_study_m4.dto.response.user;

import lombok.Data;

@Data
public class UserLoginResponseDTO {
    private Integer id;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private String message;
//    private Integer cartID;
}
