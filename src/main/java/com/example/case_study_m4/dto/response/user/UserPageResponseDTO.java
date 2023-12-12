package com.example.case_study_m4.dto.response.user;

import lombok.Data;

import java.util.List;

@Data
public class UserPageResponseDTO<UserRegisterResponseDTO> {
    private  List<UserRegisterResponseDTO> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
