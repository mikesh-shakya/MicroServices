package com.example.microServices.HotelServices.services.implementation;

import com.example.microServices.HotelServices.entities.Hotel;
import com.example.microServices.HotelServices.exceptions.ResourceNotFoundException;
import com.example.microServices.HotelServices.repositories.HotelRepository;
import com.example.microServices.HotelServices.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServicesImple implements HotelServices {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomUserId = UUID.randomUUID().toString();
        hotel.setHotelId(randomUserId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("User with given ID is not available in database !!!"));
    }

    @Override
    public Hotel updateHotel(String hotelId, Hotel hotel) {
        Hotel updatedHotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("User with given ID is not available in database !!!"));
        updatedHotel.setName(hotel.getName());
        updatedHotel.setLocation(hotel.getLocation());
        updatedHotel.setAbout(hotel.getAbout());
        return hotelRepository.save(updatedHotel);
    }

    @Override
    public void deleteHotel(String hotelId) {
        Hotel deletedHotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("User with given ID is not available in database !!!"));
        hotelRepository.delete(deletedHotel);
    }
}
