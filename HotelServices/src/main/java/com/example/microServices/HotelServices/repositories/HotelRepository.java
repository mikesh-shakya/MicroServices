package com.example.microServices.HotelServices.repositories;

import com.example.microServices.HotelServices.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
