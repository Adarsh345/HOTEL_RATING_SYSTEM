package com.lcwd.rating.service;

import com.lcwd.rating.entity.Rating;

import java.util.List;

public interface RatingService {
    //create
    Rating create(Rating rating);

    // get all ratings
    List<Rating>  getRating();

    //get all by UserId
    List<Rating> getRatingByUserId(String userId);

    //gat all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
