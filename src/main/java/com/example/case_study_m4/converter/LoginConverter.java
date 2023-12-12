package com.example.case_study_m4.converter;

import com.example.case_study_m4.dto.response.LoginResponseDTO;
import com.example.case_study_m4.entity.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
public class LoginConverter {

    public LoginResponseDTO EntityToDTO (User user)  {
        LoginResponseDTO  loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUsername(user.getUsername());
            return loginResponseDTO;
    }
}
