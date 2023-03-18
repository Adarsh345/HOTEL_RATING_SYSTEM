package com.lcwd.hotel.repository;

import com.lcwd.hotel.entity.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository
public interface HotelRepository extends MongoRepository<Hotel, Serializable> {
    Hotel findById(String hotelId);
}
