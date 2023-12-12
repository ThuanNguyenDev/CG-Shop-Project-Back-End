package com.example.case_study_m4.controller.admin;

import com.example.case_study_m4.dto.request.UserRegisterRequestDTO;
import com.example.case_study_m4.dto.response.user.UserCommonResponseDTO;
import com.example.case_study_m4.dto.response.user.UserPageResponseDTO;
import com.example.case_study_m4.dto.response.user.UserRegisterResponseDTO;
import com.example.case_study_m4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
@CrossOrigin(origins = "*")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/show")
    public ResponseEntity<UserCommonResponseDTO<UserRegisterResponseDTO>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        UserPageResponseDTO<UserRegisterResponseDTO> userPage = userService.getAllUserDTOs(pageable);

        UserCommonResponseDTO<UserRegisterResponseDTO> userCommonResponseDTO = new UserCommonResponseDTO<>();
        userCommonResponseDTO.setData(userPage);
        userCommonResponseDTO.setSuccess(true);
        userCommonResponseDTO.setMessage("Users retrieved successfully.");
        return new ResponseEntity<>(userCommonResponseDTO, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        boolean deleted = userService.deleteUser(userId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserRegisterResponseDTO>> searchUsers(@RequestParam("searchText") String searchText) {
        List<UserRegisterResponseDTO> users = userService.searchUsers(searchText);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<UserRegisterResponseDTO> searchUserByID(@PathVariable Integer id) {
        UserRegisterResponseDTO foundUser = userService.getUserByID(id);
        return new ResponseEntity<>(foundUser,HttpStatus.OK);
    }

}
