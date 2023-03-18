package com.lcwd.rating.controller;

import com.lcwd.rating.entity.Rating;
import com.lcwd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    //create rating
    @PostMapping("/")
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        Rating rating1 = ratingService.create(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRating(){
        List<Rating> ratingList = ratingService.getRating();
        return ResponseEntity.ok(ratingList);
    }
    @GetMapping("/hotelId/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingByHotelId(@PathVariable String hotelId){
        List<Rating> ratingList = ratingService.getRatingByHotelId(hotelId );
        return ResponseEntity.ok(ratingList);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingByUserId(@PathVariable String userId){
        List<Rating> ratingList = ratingService.getRatingByUserId(userId);
        return ResponseEntity.ok(ratingList);
    }


}
