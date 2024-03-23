package com.scaler.bookmyshow.modles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    @ManyToMany
    private List<ShowSeat> showSeat;
    @ManyToOne
    private User user;
    @ManyToOne
    private  Show show;
    private int amount;

    @OneToMany
    private List<Payment> payments;
}
