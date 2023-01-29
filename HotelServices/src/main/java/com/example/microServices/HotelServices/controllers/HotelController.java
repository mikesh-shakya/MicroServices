package com.example.microServices.HotelServices.controllers;

import com.example.microServices.HotelServices.entities.Hotel;
import com.example.microServices.HotelServices.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelServices hotelServices;

    //create a hotel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel createdHotel = hotelServices.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    }

    //get All hotels
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        List<Hotel> hotels = hotelServices.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    // get hotel by id
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){
        Hotel hotel = hotelServices.getHotel(hotelId);
        return ResponseEntity.status(HttpStatus.FOUND).body(hotel);
    }

    // update Hotel by id
    @PutMapping("/{hotelId}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel){
        Hotel updatedHotel = hotelServices.updateHotel(hotelId,hotel);
        return ResponseEntity.ok(updatedHotel);
    }

    // delete Hotel by id
    @DeleteMapping("{hotelId}")
    public void deleteHotel(@PathVariable String hotelId){
        hotelServices.deleteHotel(hotelId);
    }
}
