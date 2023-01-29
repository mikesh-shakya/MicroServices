package com.example.microServices.HotelServices.services;

import com.example.microServices.HotelServices.entities.Hotel;

import java.util.List;

public interface HotelServices {

    // create hotel
    Hotel createHotel(Hotel hotel);

    //get All hotel
    List<Hotel> getAllHotels();

    //get hotel by Id
    Hotel getHotel(String hotelId);

    //update hotel by id
    Hotel updateHotel(String hotelId, Hotel hotel);

    // delete hotel by Id
    void deleteHotel(String hotelId);
}
