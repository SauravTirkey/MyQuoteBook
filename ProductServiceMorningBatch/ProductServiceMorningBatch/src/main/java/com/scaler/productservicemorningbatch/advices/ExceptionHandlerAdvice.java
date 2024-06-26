package com.scaler.productservicemorningbatch.advices;

import com.scaler.productservicemorningbatch.dtos.ArithmeticExceptionDto;
import com.scaler.productservicemorningbatch.dtos.ArrayIndexOutOfBoundExceptionDto;
import com.scaler.productservicemorningbatch.dtos.ExceptionDto;
import com.scaler.productservicemorningbatch.exceptions.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    //everythimnmg is going to use the same controller advice
    public ResponseEntity<ArithmeticExceptionDto> handleArithmeticException(){
        ArithmeticExceptionDto dto=new ArithmeticExceptionDto();
        dto.setMessage("Something went wrong");
        dto.setDetails("Some random details");
        return  new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ArrayIndexOutOfBoundExceptionDto> handleAIOBException(){
        return null;
    }
    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<ExceptionDto> handleInvalidProductIdException(InvalidProductIdException exception){
       ExceptionDto exceptionDto=new ExceptionDto();
       exceptionDto.setProductId(exception.getProductId());
       exceptionDto.setMessage("Invalid productId passed please retry with valid product id");
       return new ResponseEntity<>(exceptionDto,HttpStatus.BAD_REQUEST);
    }
}
