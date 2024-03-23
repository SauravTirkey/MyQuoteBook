package com.scaler.bookmyshow.controllers;

import com.scaler.bookmyshow.dtos.BookMovieRequestDto;
import com.scaler.bookmyshow.dtos.BookMovieResponseDto;
import com.scaler.bookmyshow.dtos.ResponseStatus;
import com.scaler.bookmyshow.modles.Booking;
import com.scaler.bookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }

   public BookMovieResponseDto bookMovie(BookMovieRequestDto request){
         BookMovieResponseDto bookMovieResponseDto =new BookMovieResponseDto();

          Booking booking;
          try{
              booking=bookingService.bookMovie(
                      request.getShowSeatId(),
                      request.getUserId(),
                      request.getShowId()
              );

              bookMovieResponseDto.setBookingId(booking.getId());
              bookMovieResponseDto.setAmount(booking.getAmount());
              bookMovieResponseDto.setResponseStatus(ResponseStatus.SUCCESS);//this is the api status not booking status
          }catch(Exception ex){
             bookMovieResponseDto.setResponseStatus(ResponseStatus.FAILURE);
       }


       return bookMovieResponseDto;
    }
}
