package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dtos.ResponseStatus;
import com.scaler.bookmyshow.dtos.SignUpRequestDto;
import com.scaler.bookmyshow.dtos.SignUpResponseDto;
import com.scaler.bookmyshow.modles.User;
import com.scaler.bookmyshow.repositories.UserRepository;
import com.scaler.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserController {
  private UserService userService;
  public UserController(UserService userService){
      this.userService=userService;
  }

  public SignUpResponseDto signUp(SignUpRequestDto request){

      SignUpResponseDto signUpResponseDto=new SignUpResponseDto();

      User user;
      try{
          user=userService.signUp(request.getEmail()
                  ,request.getPassword());
          signUpResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
          signUpResponseDto.setUserId(user.getId());

      }catch(Exception ex){
          signUpResponseDto.setResponseStatus(ResponseStatus.FAILURE);
      }


      return  signUpResponseDto;
  }
}
