package com.lcwd.user.service.controller;

import com.lcwd.user.service.entitys.User;
import com.lcwd.user.service.service.UserService;
import com.lcwd.user.service.service.serviceImpl.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService ;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(path = "/")
    public ResponseEntity<User> createUser( @RequestBody  User user){
        User user1 = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name ="ratingHotelBreaker" , fallbackMethod = "ratingHotelFallBack")

    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
       User user =  userService.getUser(userId);
       return ResponseEntity.ok(user);
    }

    // CREATING FALLBACK METHOD FOR CIRCUIT BREAKER
     public ResponseEntity<User>  ratingHotelFallBack(String userId, Exception exception){
        logger.info("Fallback is executed because service is down :", exception.getMessage());
       User  user =  User.builder()
               .name("Dummy")
               .email("dummy101gmail.com")
               .about("This user is Dummy because service is  down")
               .id("123553")
               .build();

        return new ResponseEntity<>(user , HttpStatus.OK);

     }

    @GetMapping ("/")
    public ResponseEntity<List<User>>  getAlluser(){
        List<User> alluser = userService.getAlluser();
        return ResponseEntity.ok(alluser);
    }


}

