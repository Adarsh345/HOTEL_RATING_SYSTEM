package com.lcwd.user.service.entitys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedBack;
    private Hotel hotel;
}
