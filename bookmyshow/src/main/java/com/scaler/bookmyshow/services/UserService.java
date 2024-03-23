package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.modles.User;
import com.scaler.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    public User signUp(String email, String password){

        Optional<User> userOptional=userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw  new RuntimeException();
        }

        User user=userOptional.get();

        user.setEmail(email);
        user.setPassword(password);

        User saveUser=userRepository.save(user);
        return  user;

    }
}
