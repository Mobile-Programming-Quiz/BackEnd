package com.quiz_app.service;


import com.quiz_app.dao.UserRepository;
import com.quiz_app.model.LoginDTO;
import com.quiz_app.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean loginProcess(LoginDTO loginDTO) {
        UserEntity user = userRepository.findByUsername(loginDTO.getUsername());

        if (user != null && bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return true;
        }
        return false;
    }
}

