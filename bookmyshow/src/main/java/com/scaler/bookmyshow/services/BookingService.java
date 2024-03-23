package com.scaler.bookmyshow.services;

import com.scaler.bookmyshow.modles.*;
import com.scaler.bookmyshow.repositories.BookingRepository;
import com.scaler.bookmyshow.repositories.ShowRepository;
import com.scaler.bookmyshow.repositories.ShowSeatRepository;
import com.scaler.bookmyshow.repositories.UserRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private PriceCalculatorService priceCalculatorService;

    private BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository
    ,ShowRepository showRepository
    ,ShowSeatRepository showSeatRepository
    , PriceCalculatorService priceCalculatorService
    ,BookingRepository bookingRepository){
        this.userRepository=userRepository;
        this.showRepository=showRepository;
        this.showSeatRepository=showSeatRepository;
        this.priceCalculatorService=priceCalculatorService;
        this.bookingRepository=bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(List<Long> seatsIds
    , Long userId
    , Long showId){


        /*
        * 1.Get the user with user id (also check if the user exists)
        * 2.get the show details
        * 3.get the show seats with the given show seat id
        * 4.check if the show seats are available
        * 5.If they are not available throw an error
        * 6.if they are available update teh status  to blocked and update the timestamp
        * 7.update teh  show seats  in db
        * 8.return
        * ////end the lock here
        *
        * */
        Optional<User> userOptional=userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException();
        }
        User user=userOptional.get();

        Optional<Show> showOptional=showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new RuntimeException();
        }
        Show show=showOptional.get();

        List<ShowSeat> showSeats=showSeatRepository.findAllById(seatsIds);

         for(ShowSeat showSeat:showSeats){
             if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)||
             showSeat.getShowSeatStatus().equals(ShowSeatStatus.BOOKED)&&
             Duration.between(showSeat.getBlockedAt().toInstant(),new Date().toInstant()).toMinutes()>15)){
                    throw new RuntimeException();
             }
         }
         List<ShowSeat> savedShowSeat=new ArrayList<>();
         for(ShowSeat showSeat:showSeats){
             showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
             showSeat.setBlockedAt(new Date());
             savedShowSeat.add(showSeatRepository.save(showSeat));
         }
      //after setting we are sending the booking object
            Booking booking =new Booking();
            booking.setBookingStatus((BookingStatus.PENDING));
            booking.setShowSeat(savedShowSeat);
            booking.setUser(user);
            booking.setShow(show);
            booking.setAmount(priceCalculatorService.calculatePrice(savedShowSeat,show));

            //saving is necessary
                   Booking savedBooking=bookingRepository.save(booking);


            //we are waiting for the payment

            return booking;
     }
}
