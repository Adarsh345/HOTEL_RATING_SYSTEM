package com.lcwd.hotel.service.serviceImpl;

import com.lcwd.hotel.entity.Hotel;
import com.lcwd.hotel.exception.ResourceNotFoundException;
import com.lcwd.hotel.repository.HotelRepository;
import com.lcwd.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }


    @Override
    public Hotel getSingleHotel(String hotelId) {
      Hotel hotel = hotelRepository.findById(hotelId);
        if(hotel == null){
            throw new ResourceNotFoundException("Hotel not found for this passed id : "+hotelId);
        }
        return hotel;
    }
}
