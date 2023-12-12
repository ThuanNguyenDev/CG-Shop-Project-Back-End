package com.example.case_study_m4.service;

import com.example.case_study_m4.dto.request.user.LoginRequestDTO;
import com.example.case_study_m4.dto.request.user.RegisterRequestDTO;
import com.example.case_study_m4.dto.response.LoginResponseDTO;
import com.example.case_study_m4.dto.response.RegisterResponseDTO;
import com.example.case_study_m4.dto.response.user.UserPageResponseDTO;
import com.example.case_study_m4.dto.response.user.UserRegisterResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface UserService {

    UserPageResponseDTO<UserRegisterResponseDTO> getAllUserDTOs(Pageable pageable);

    UserRegisterResponseDTO getUserByID(Integer id);
    boolean deleteUser(Integer userId);
    List<UserRegisterResponseDTO> searchUsers(String searchText);

    RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

}

