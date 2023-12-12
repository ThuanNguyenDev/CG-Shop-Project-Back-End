package com.example.case_study_m4.converter;

import com.example.case_study_m4.dto.response.user.UserLoginResponseDTO;
import com.example.case_study_m4.dto.response.user.UserRegisterResponseDTO;
import com.example.case_study_m4.entity.User;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {



    public UserRegisterResponseDTO registerFromUserToUserDTO(User user) {
        UserRegisterResponseDTO userRegisterResponseDTO = new UserRegisterResponseDTO();
        userRegisterResponseDTO.setId(user.getId());
        userRegisterResponseDTO.setUsername(user.getUsername());
        userRegisterResponseDTO.setEmail(user.getEmail());
        userRegisterResponseDTO.setPhoneNumber(user.getPhoneNumber());
        userRegisterResponseDTO.setAddress(user.getAddress());
        return userRegisterResponseDTO;
    }

    public List<UserRegisterResponseDTO> transferUserListToUserDTOList(List<User> userList) {
        List<UserRegisterResponseDTO> userRegisterResponseDTOList = new ArrayList<>();
        for (User user : userList) {
            userRegisterResponseDTOList.add(registerFromUserToUserDTO(user));
        }
        return userRegisterResponseDTOList;
    }



    public UserLoginResponseDTO transferUserLoginToUserDTOLogin (User user) {
        UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO();
       userLoginResponseDTO.setId(user.getId());
        userLoginResponseDTO.setUsername(user.getUsername());
        userLoginResponseDTO.setEmail(user.getEmail());
        userLoginResponseDTO.setPhoneNumber(user.getPhoneNumber());
        userLoginResponseDTO.setAddress(user.getAddress());
        return userLoginResponseDTO;
    }

    public List<UserLoginResponseDTO> transferUserLoginListToUserDTOLoginList(List<User> userList){
        List<UserLoginResponseDTO> userLoginResponseDTOList = new ArrayList<>();
        for (User user : userList) {
            userLoginResponseDTOList.add(transferUserLoginToUserDTOLogin(user));
        }
        return userLoginResponseDTOList;
    }

}
