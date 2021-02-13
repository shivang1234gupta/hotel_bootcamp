package com.example.hotel.service;

import com.example.hotel.model.Hotel;
import com.example.hotel.repository.HotelCrud;
import com.example.hotel.repository.HotelRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRep hotelRep;

    @Autowired
    private HotelCrud hotelCrud;

    public List<Hotel> getAllUserInfo() {
        return hotelRep.findAllHotelDetailsFromElastic();
    }

    public void hotelsave(Hotel s){
        hotelCrud.save(s);
    }

    public void deletehotel(String id) {
        hotelCrud.deleteById(id);
    }

    @Transactional
    public Hotel updateuser(String id, String status) {
        Hotel hotel=hotelCrud.findById(id).orElseThrow(()->new IllegalStateException("user not exist"));
        hotel.setHotel_status(status);
        hotelCrud.save(hotel);
        return hotel;
    }

    public List<Hotel> getAllUserByNameInfo(String country,String city) {
        System.out.println("Hello");
        return hotelRep.findAllHotelByCountryAndCityDetailsFromElastic(country, city);
    }

    public Hotel gethotelById(String id) {
        System.out.println("Hello");
        Hotel hotel= hotelCrud.findById(id).orElseThrow(()->new IllegalStateException("hotelid not exist"));
        return hotel;
    }
}
