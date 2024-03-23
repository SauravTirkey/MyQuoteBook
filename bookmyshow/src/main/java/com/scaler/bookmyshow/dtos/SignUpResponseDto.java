package com.scaler.bookmyshow.dtos;

import com.scaler.bookmyshow.modles.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {

    private Long userId;
    private ResponseStatus responseStatus;
}
