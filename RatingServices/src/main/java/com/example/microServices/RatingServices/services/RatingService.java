package com.example.microServices.RatingServices.services;

import com.example.microServices.RatingServices.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating createRating(Rating rating);

    //get all Ratings
    List<Rating> getAllRating();

    // get all rating by UserId
    List<Rating> getRatingByUserId(String userId);

    // get all rating by HotelId
    List<Rating> getRatingByHotelId(String hotelId);
}
