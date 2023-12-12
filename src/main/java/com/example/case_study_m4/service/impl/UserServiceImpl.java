package com.example.case_study_m4.service.impl;

import com.example.case_study_m4.constraint.ERole;
import com.example.case_study_m4.converter.LoginConverter;
import com.example.case_study_m4.converter.RegisterConverter;
import com.example.case_study_m4.converter.UserConverter;
import com.example.case_study_m4.dto.request.user.LoginRequestDTO;
import com.example.case_study_m4.dto.request.user.RegisterRequestDTO;
import com.example.case_study_m4.dto.request.UserLoginRequestDTO;
import com.example.case_study_m4.dto.response.LoginResponseDTO;
import com.example.case_study_m4.dto.response.RegisterResponseDTO;
import com.example.case_study_m4.dto.response.user.UserLoginResponseDTO;
import com.example.case_study_m4.dto.response.user.UserPageResponseDTO;
import com.example.case_study_m4.dto.request.UserRegisterRequestDTO;
import com.example.case_study_m4.dto.response.user.UserRegisterResponseDTO;
import com.example.case_study_m4.entity.ShoppingCart;
import com.example.case_study_m4.entity.User;
import com.example.case_study_m4.repository.ShoppingCartRepository;
import com.example.case_study_m4.repository.UserRepository;
import com.example.case_study_m4.config.security.JwtTokenUtil;
import com.example.case_study_m4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private RegisterConverter registerConverter;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserDetailsService userDetailsService;


    @Autowired
    private LoginConverter loginConverter;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;





    @Override
    public UserPageResponseDTO<UserRegisterResponseDTO> getAllUserDTOs(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);

         List<UserRegisterResponseDTO> userRegisterResponseDTOList = userPage
                .getContent()
                .stream()
                .map(userConverter::registerFromUserToUserDTO)
                .collect(Collectors.toList());

        UserPageResponseDTO<UserRegisterResponseDTO> pageResponseDTO = new UserPageResponseDTO<>();
        pageResponseDTO.setContent(userRegisterResponseDTOList);
        pageResponseDTO.setPage(userPage.getNumber());
        pageResponseDTO.setSize(userPage.getSize());
        pageResponseDTO.setTotalElements(userPage.getTotalElements());
        pageResponseDTO.setTotalPages(userPage.getTotalPages());

        return pageResponseDTO;
    }




    @Override
    public UserRegisterResponseDTO getUserByID(Integer id) {
        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userConverter.registerFromUserToUserDTO(foundUser);
    }


    @Override
    public boolean deleteUser(Integer userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }


    @Override
    public List<UserRegisterResponseDTO> searchUsers(String searchText) {
        List<User> users = userRepository.findByUsernameLikeIgnoreCase(searchText);
        return userConverter.transferUserListToUserDTOList(users);
    }



    @Override
    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        String password = registerRequestDTO.getPassword();
        String hashCode = passwordEncoder.encode(password);
        System.out.println(hashCode);

        User newUser = User.builder().username(registerRequestDTO.getUsername())
                .password(hashCode)
                .email(registerRequestDTO.getEmail())
                .phoneNumber(registerRequestDTO.getPhoneNumber())
                .address(registerRequestDTO.getAddress())
                .role("ROLE_".concat(ERole.USER.toString()))
                .build();

        userRepository.save(newUser);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(newUser);
        shoppingCartRepository.save(shoppingCart);


        return registerConverter.entityToDTO(newUser);

    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        String password = loginRequestDTO.getPassword();
        if (password != null) {
            User user = userRepository.findByUsername(loginRequestDTO.getUsername());

            if (user != null) {
                if (passwordEncoder.matches(password, user.getPassword())) {
                    LoginResponseDTO loginResponseDTO = loginConverter.EntityToDTO(user);
                    loginResponseDTO.setMessage("Logged in successfully !");

                    String token = jwtTokenUtil.generateToken(user);
                    loginResponseDTO.setToken(token);
                    loginResponseDTO.setRole(user.getRole());

                    return loginResponseDTO;
                }
                else {
                    throw new RuntimeException("Wrong password");
                }
            }
        }
        return null;
    }

}


