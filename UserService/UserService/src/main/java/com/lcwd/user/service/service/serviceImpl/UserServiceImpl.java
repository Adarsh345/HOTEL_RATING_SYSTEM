package com.lcwd.user.service.service.serviceImpl;

import com.lcwd.user.service.entitys.Hotel;
import com.lcwd.user.service.entitys.Rating;
import com.lcwd.user.service.entitys.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelServices;
import com.lcwd.user.service.repository.UserRepository;
import com.lcwd.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelServices hotelServices;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
       return  userRepository.save(user);
    }

    @Override
    public List<User> getAlluser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
      User user =  userRepository.findById(userId);
        if(user == null   ){
            throw new ResourceNotFoundException("user not found exception :"+userId);
        }
         //url to call rating service
        // http://localhost:8083/ratings/userId/63e48a6e51bea717af7b0526
       Rating[] ratingsOfUsers = restTemplate.getForObject("http://RATING-SERVICE/ratings/userId/"+user.getId() , Rating[].class);
        logger.info("{}",ratingsOfUsers);
        List<Rating> ratings = Arrays.stream(ratingsOfUsers).toList();
       List<Rating> ratingList =  ratings.stream().map(rating -> {
         //  http://localhost:8082/hotel/63e4cb1be5dffc1963c752e2
        //  ResponseEntity<Hotel> hotelResponseEntity =  restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
           // Hotel hotel =  hotelResponseEntity.getBody();
           Hotel hotel = hotelServices.getHotel(rating.getHotelId());
         rating.setHotel(hotel);
         return rating;
        }).collect(Collectors.toList());

      user.setRatings(ratings);


      return user;
    }

}
