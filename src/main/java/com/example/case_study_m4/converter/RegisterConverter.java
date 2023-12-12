package com.example.case_study_m4.converter;

import com.example.case_study_m4.dto.response.RegisterResponseDTO;
import com.example.case_study_m4.entity.User;
import org.springframework.stereotype.Component;

@Component
public class RegisterConverter {
    public RegisterResponseDTO entityToDTO(User newUser) {
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        registerResponseDTO.setId(newUser.getId());
        registerResponseDTO.setUsername(newUser.getUsername());
        registerResponseDTO.setEmail(newUser.getEmail());
        registerResponseDTO.setPhoneNumber(newUser.getPhoneNumber());
        registerResponseDTO.setAddress(newUser.getAddress());
        return registerResponseDTO;
    }


}
