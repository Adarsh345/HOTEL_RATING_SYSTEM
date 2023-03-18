package com.lcwd.user.service.external.services;

import com.lcwd.user.service.entitys.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelServices {
    @GetMapping("/hotel/{hotelId}")
     Hotel getHotel(@PathVariable(value = "hotelId") String hotelId);
}
