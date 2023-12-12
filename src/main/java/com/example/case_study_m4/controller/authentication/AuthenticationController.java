package com.example.case_study_m4.controller.authentication;

import com.example.case_study_m4.constraint.Header;
import com.example.case_study_m4.dto.request.user.LoginRequestDTO;
import com.example.case_study_m4.dto.request.user.RegisterRequestDTO;
import com.example.case_study_m4.dto.response.LoginResponseDTO;
import com.example.case_study_m4.dto.response.RegisterResponseDTO;
//import com.example.case_study_m4.security.JwtTokenProvider;
import com.example.case_study_m4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/authen")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
       RegisterResponseDTO registerResponseDTO =  userService.register(registerRequestDTO);
        return new ResponseEntity<>(registerResponseDTO,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO,
                                   HttpServletResponse response) {
        LoginResponseDTO loginResponseDTO = userService.login(loginRequestDTO);
        String token = loginResponseDTO.getToken();

        if(token != null) {
            response.addHeader(String.valueOf(Header.Authorization), token);
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(loginResponseDTO, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
