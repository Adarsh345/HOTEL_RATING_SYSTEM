package com.lcwd.hotel.service;

import com.lcwd.hotel.entity.Hotel;

import java.util.List;

public interface HotelService {
    //create
    Hotel createHotel(Hotel hotel);

    //get all
    List<Hotel> getAll();

    //get single
    Hotel getSingleHotel(String hotelId);


}
