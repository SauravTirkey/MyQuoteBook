package com.scaler.bookmyshow.dtos;

import com.scaler.bookmyshow.modles.ShowSeat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookMovieRequestDto {
    private List<Long> showSeatId;
    private Long userId;
    private Long showId;
}
