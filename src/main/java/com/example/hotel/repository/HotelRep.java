package com.example.hotel.repository;

import com.example.hotel.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRep {

    List<Hotel> findAllHotelDetailsFromElastic();

    List<Hotel> findAllHotelByCountryAndCityDetailsFromElastic(String countryid,String cityid);

}
