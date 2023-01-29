package com.example.microServices.UserServices.services.implementation;

import com.example.microServices.UserServices.entities.Hotel;
import com.example.microServices.UserServices.entities.Rating;
import com.example.microServices.UserServices.entities.User;
import com.example.microServices.UserServices.exceptions.ResourceNotFoundException;
import com.example.microServices.UserServices.repositories.UserRepository;
import com.example.microServices.UserServices.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User createUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        // getting user data from user repository...
        User getUser =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given ID is not available in database !!!"));
        // fetching rating for the above user from Rating Services
//        http://localhost:8083/ratings/users/userId
        Rating[] userRatings =  restTemplate.getForObject("http://RATING-SERVICES/ratings/users/" + getUser.getUserId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(userRatings).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            // api call to hotel service to get the hotel
            ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity("http://HOTEL-SERVICES/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelResponseEntity.getBody();
            //set the hotel to rating
            rating.setHotel(hotel);
            // return ratings
            return rating;
        }).toList();

        getUser.setRatings(ratingList);
        return getUser;
    }

    @Override
    public User updateUser(String userId, User user) {
        User updateUser =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given ID is not available in database !!!"));
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        updateUser.setAbout(user.getAbout());
        return userRepository.save(updateUser);
    }

    @Override
    public void deleteUser(String userId) {
        User deleteUser =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given ID is not available in database !!!"));
        userRepository.delete(deleteUser);
    }
}
